package fun.kolowert.c92b.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

	public static final int ITERATIONS = 1000;
	public static final int KEY_LENGTH = 128;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final SecureRandom RAND = new SecureRandom();

	public static Optional<String> hashTextPassword(String plainTextPassword, String salt) {
		char[] chars = plainTextPassword.toCharArray();
		byte[] bytes = salt.getBytes();
		Optional<String> result = Optional.empty();
		
		PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
		Arrays.fill(chars, Character.MIN_VALUE);
		try {
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			result = Optional.of(Base64.getEncoder().encodeToString(securePassword));
			return result;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			// TODO Handle the exception
			return Optional.empty();
		} finally {
			spec.clearPassword();
			System.out.println("PasswordUtils#hashTextPassword >> finally hash: " + result);
		}
	}

	public static boolean verifyPassword(String textPassword, String hashedPassword, String salt) {
		Optional<String> optEncrypted = hashTextPassword(textPassword, salt);
		if (!optEncrypted.isPresent()) {
			return false;
		}
		return optEncrypted.get().equals(hashedPassword);
	}

	public static String generateSalt(final int length) {
		byte[] salt = new byte[length];
		RAND.nextBytes(salt);

		Optional<String> preSalt = Optional.of(Base64.getEncoder().encodeToString(salt));

		if (preSalt.isPresent()) {
			return preSalt.toString();
		}

		return "f2Ts5iF8";
	}

}
