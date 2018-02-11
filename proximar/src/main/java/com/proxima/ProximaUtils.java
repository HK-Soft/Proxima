package com.proxima;

import org.apache.commons.text.RandomStringGenerator;

import com.proxima.core.model.FileType;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.rmi.AccessException;
import java.util.UUID;

public class ProximaUtils {

	private static int DEFAULT_RANDOM_STRING_COUNT = 8;
	public static String DEFAULT_MEASURE_OF_UNIT_KEY = "product.measureunit.default";

	private ProximaUtils() throws AccessException {
		throw new AccessException("");
	}

	/**
	 * 
	 * Generate a random Alpha-numerical String
	 * 
	 * @param count
	 *            the length of the generated string
	 * @return a random string
	 */
	public static String randomString(int count) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				// .withinRange('A', 'Z')
				.filteredBy(LETTERS, DIGITS).build();
		return generator.generate(count);
	}

	/**
	 * 
	 * Generate a random Alpha-numerical String with the size of 8 Characters (in
	 * UpperCase)
	 * 
	 * @return a random string
	 * 
	 */
	public static String randomString() {
		return randomString(DEFAULT_RANDOM_STRING_COUNT).toUpperCase();
	}

	/**
	 * 
	 * creating a token according to the UUID Specification removing the '-'
	 * separator
	 * 
	 * @return 128-bit size string
	 * 
	 */
	public static String generateRandomToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static FileType getFileType(String file) {
		int dotPos = file.lastIndexOf('.');
		if (dotPos == -1)
			return null;
		try {
			String type = file.substring(dotPos + 1, file.length());
			for (FileType fileType : FileType.values())
				if (type.equalsIgnoreCase(fileType.name()))
					return fileType;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("getFileType " + e.getMessage());
		}
		return null;
	}

}
