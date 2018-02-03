package com.proximar;

import org.apache.commons.text.RandomStringGenerator;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.rmi.AccessException;
import java.util.UUID;

public class ProximarUtils {

	private static int DEFAULT_RANDOM_STRING_COUNT = 8;
	public static String DEFAULT_MEASURE_OF_UNIT_KEY = "product.measureunit.default";

	private ProximarUtils() throws AccessException {
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

}
