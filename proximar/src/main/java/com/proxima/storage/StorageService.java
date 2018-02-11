package com.proxima.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.proxima.core.model.Document;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    Document store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
