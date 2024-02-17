/**
 * The program(s) herein may be used  freely
 * for personal use only. It may not be sold, 
 * rented, leased, sublicensed  or  modified 
 * without permission of  the  author.  
 * 
 *           www.carlosbcruz.com
 *           
 * This program is provided "AS IS"  without 
 * warranty of any kind. In no event I  will
 * be liable  for  any  direct  or  indirect 
 * damage.
 */

package com.carlosbcruz.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Provides encryption functionalities to the passwords.
 */
public class PasswordUtil {

	// Cipher algorithm.
	private static String CIPHER_INSTANCE_NAME = "AES"; //$NON-NLS-1$
	// Number of bits used for encryption.
	private static int NUMBER_OF_BITS = 128;
	// Separator for bytes in encrypted password
	private static String ENCRYPTION_BYTES_SEPARATOR = "."; //$NON-NLS-1$

	/**
	 * Transform a password into a printable string.
	 * 
	 * @param bytes
	 *            The password.
	 * @return String The password in a printable version.
	 */
	private static String passwordToPrintableText(byte[] bytes) {

		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {

			byte b = bytes[i];

			stringBuffer.append(0x00FF & b);

			// Do not add a separator to the last position.
			if (i + 1 < bytes.length) {
				stringBuffer.append(ENCRYPTION_BYTES_SEPARATOR);
			}
		}

		return stringBuffer.toString();
	}

	/**
	 * Convert the password from a string to a sequence of bytes.
	 * 
	 * @param password
	 *            The password in a string form.
	 * @return byte[] The bytes from the password.
	 */
	private static byte[] printableTextToPassword(String password) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		StringTokenizer st = new StringTokenizer(password,
				ENCRYPTION_BYTES_SEPARATOR, false);

		while (st.hasMoreTokens()) {

			int element = Integer.parseInt(st.nextToken());

			outputStream.write((byte) element);
		}

		return outputStream.toByteArray();
	}

	/**
	 * Generate a new encryption password.
	 * 
	 * @return A string representing an encryption password.
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateNewEncryptionkey()
			throws NoSuchAlgorithmException {

		KeyGenerator keyGenerator = KeyGenerator
				.getInstance(CIPHER_INSTANCE_NAME);
		keyGenerator.init(NUMBER_OF_BITS);
		SecretKey secretKey = keyGenerator.generateKey();

		return new String(passwordToPrintableText(secretKey.getEncoded()));
	}

	/**
	 * Encrypt a password with the provided encryption key.
	 * 
	 * @param encryptionKey
	 *            The encryption key to use in the encryption of the password.
	 * @param password
	 *            The password to be encrypted.
	 * @return The encrypted password in a printable version.
	 * @throws InvalidKeyException
	 *             Encryption exception.
	 * @throws NoSuchAlgorithmException
	 *             Encryption exception.
	 * @throws BadPaddingException
	 *             Encryption exception.
	 * @throws IllegalBlockSizeException
	 *             Encryption exception.
	 * @throws NoSuchPaddingException
	 *             Encryption exception.
	 */
	public static String encryptPassword(String encryptionKey, String password)
			throws InvalidKeyException, NoSuchAlgorithmException,
			BadPaddingException, IllegalBlockSizeException,
			NoSuchPaddingException {

		SecretKeySpec secretKey = new SecretKeySpec(
				printableTextToPassword(encryptionKey), CIPHER_INSTANCE_NAME);

		Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		return passwordToPrintableText(cipher.doFinal(password.getBytes()))
				.toString();
	}

	/**
	 * Decrypt a password using the provided encryption key.
	 * 
	 * @param encryptionKey
	 * @param encryptedPassword
	 * @return The decrypted password.
	 * @throws InvalidKeyException
	 *             Encryption exception.
	 * @throws NoSuchAlgorithmException
	 *             Encryption exception.
	 * @throws BadPaddingException
	 *             Encryption exception.
	 * @throws IllegalBlockSizeException
	 *             Encryption exception.
	 * @throws NoSuchPaddingException
	 *             Encryption exception.
	 */
	public static String decryptPassword(String encryptionKey,
			String encryptedPassword) throws InvalidKeyException,
			NoSuchAlgorithmException, BadPaddingException,
			IllegalBlockSizeException, NoSuchPaddingException {

		SecretKeySpec secretKey = new SecretKeySpec(
				printableTextToPassword(encryptionKey), CIPHER_INSTANCE_NAME);

		Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);

		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		return new String(
				cipher.doFinal(printableTextToPassword(encryptedPassword)));
	}

	/**
	 * Verify returns true if the specified text is encrypted, false otherwise
	 */
	public static boolean isEncrypted(String text) {

		if (text.indexOf(ENCRYPTION_BYTES_SEPARATOR) == -1) {
			return false;
		}

		StringTokenizer st = new StringTokenizer(text,
				ENCRYPTION_BYTES_SEPARATOR, false);
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.length() > 3) {
				return false;
			}
			for (char character : token.toCharArray()) {
				if (!Character.isDigit(character)) {
					return false;
				}
			}
		}
		return true;
	}

}