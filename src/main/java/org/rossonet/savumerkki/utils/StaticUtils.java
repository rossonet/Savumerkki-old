package org.rossonet.savumerkki.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.ConfigurationException;

public class StaticUtils {

	private static final String ENCRYPTION_ALGORITHM = "AES";
	private static final String PROTOCOL_SEPARATOR = "://";

	public static void changeLogLevel(final String logLevel) throws ConfigurationException {
		final Logger rootLogger = Logger.getLogger("");
		Level targetLevel = Level.INFO;
		switch (logLevel) {
		case "all":
			targetLevel = Level.ALL;
			break;
		case "config":
			targetLevel = Level.CONFIG;
			break;
		case "fine":
			targetLevel = Level.FINE;
			break;
		case "finer":
			targetLevel = Level.FINER;
			break;
		case "finest":
			targetLevel = Level.FINEST;
			break;
		case "info":
			targetLevel = Level.INFO;
			break;
		case "off":
			targetLevel = Level.OFF;
			break;
		case "severe":
			targetLevel = Level.SEVERE;
			break;
		case "warning":
			targetLevel = Level.WARNING;
			break;
		default:
			throw new ConfigurationException("log level " + logLevel
					+ " not exists. You can use: all, config, fine, finer, finest, info, off, severe or warning");
		}
		rootLogger.setLevel(targetLevel);
		for (final Handler handler : rootLogger.getHandlers()) {
			handler.setLevel(targetLevel);
		}
	}

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

	public static String getProtoInUrl(final String url) {
		return url.split(PROTOCOL_SEPARATOR)[0];
	}

	public static List<String> splitFixSize(final String s, final int chunkSize) {
		final List<String> chunks = new ArrayList<>();
		for (int i = 0; i < s.length(); i += chunkSize) {
			chunks.add(s.substring(i, Math.min(s.length(), i + chunkSize)));
		}
		return chunks;
	}

	private StaticUtils() {
		throw new UnsupportedOperationException("Just for static usage");
	}

}
