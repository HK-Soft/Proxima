package com.proxima.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.proxima.ProximaUtils;
import com.proxima.core.model.Document;
import com.proxima.core.model.FileType;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public Document store(MultipartFile file) {
		FileType fileType = ProximaUtils.getFileType(file.getOriginalFilename());

		if (fileType == null)
			throw new StorageException("Unsuported file type " + file.getOriginalFilename(), null);

		Document doc = new Document(StringUtils.cleanPath(file.getOriginalFilename()), fileType);

		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + doc.getOriginalName());
			}
			if (doc.getOriginalName().contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + doc.getOriginalName());
			}
			Files.copy(file.getInputStream(),
					this.rootLocation.resolve(doc.getCurrentName().concat(".").concat(doc.getType().name())),
					StandardCopyOption.REPLACE_EXISTING);
			return doc;
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + doc.getOriginalName(), e);
		}

	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			System.out.println("fil file " + file);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
