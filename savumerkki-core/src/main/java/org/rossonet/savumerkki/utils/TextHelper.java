package org.rossonet.savumerkki.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public final class TextHelper {

	private static final String ENCRYPTION_ALGORITHM = "AES";

	public static String convertByteArrayToHexString(final byte[] arrayBytes) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	public static byte[] decryptData(final byte[] encryptedData, final byte[] key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		final SecretKeySpec k = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, k);
		return c.doFinal(encryptedData);
	}

	public static void deleteDirectory(final File file) {
		if (Files.exists(Paths.get(file.getAbsolutePath()))) {
			for (final File subfile : file.listFiles()) {
				if (subfile.isDirectory()) {
					deleteDirectory(subfile);
				}
				subfile.delete();
			}
		}
	}

	public static byte[] encryptData(final byte[] dataToEncrypt, final byte[] key) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		final Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		final SecretKeySpec k = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, k);
		return c.doFinal(dataToEncrypt);
	}

	public static List<String> splitFixSize(final String s, final int chunkSize) {
		final List<String> chunks = new ArrayList<>();
		for (int i = 0; i < s.length(); i += chunkSize) {
			chunks.add(s.substring(i, Math.min(s.length(), i + chunkSize)));
		}
		return chunks;
	}

	private TextHelper() {
		throw new UnsupportedOperationException("Just for static usage");
	}

}
