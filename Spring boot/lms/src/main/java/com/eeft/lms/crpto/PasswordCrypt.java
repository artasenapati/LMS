package com.eeft.lms.crpto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordCrypt {
	private static Cipher ecipher;

	private static Cipher dcipher;

	static {
		try {
			byte[] des = "[B@1feca641123654645[Bfe".getBytes();
			SecretKeySpec sk = new SecretKeySpec(des, "DESede");
			ecipher = Cipher.getInstance("DESede");
			dcipher = Cipher.getInstance("DESede");
			ecipher.init(Cipher.ENCRYPT_MODE, sk);
			dcipher.init(Cipher.DECRYPT_MODE, sk);

		} catch (javax.crypto.NoSuchPaddingException e) {
		} catch (java.security.NoSuchAlgorithmException e) {
		} catch (java.security.InvalidKeyException e) {
		}
	}

	public synchronized static String encrypt(String str) {    
		if (str != null) {
			try {
				// Encode the string into bytes using utf-8
				byte[] utf8 = str.getBytes("UTF8");
	
				// Encrypt
				byte[] enc = ecipher.doFinal(utf8);
	
				// Encode bytes to base64 to get a string
				return new Base64().encodeAsString(enc);
			} catch (javax.crypto.BadPaddingException e) {
			} catch (IllegalBlockSizeException e) {
			} catch (UnsupportedEncodingException e) {
			}
		}
		return null;
	}

    public synchronized static String decrypt(String str) {	
		try {
			// Decode base64 to get bytes
			byte[] dec = new Base64().decodeBase64(str);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
		return null;
	}
    
    public static void main(String[] args) {
		System.out.println(encrypt("test"));
		System.out.println(decrypt(encrypt("test")));
	}

}
