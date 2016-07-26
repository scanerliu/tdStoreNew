package com.tiandu.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class MD5 {
	
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	// 默认md5 32位
	public static String md5(String input) {
		String md5Str = "";
		try {
			md5Str = code(input, 32, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	// 32位
	public static String md5For32Bit(String input) {
		String md5Str = "";
		try {
			md5Str = code(input, 32, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	// 16位
	public static String md5For16Bit(String input) {
		String md5Str = "";
		try {
			md5Str = code(input, 16, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	// 自定义编码类型
	public static String md5(String input, String encode) {
		String md5Str = "";
		try {
			md5Str = code(input, 32, encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	public static String code(String input, int bit, String encode) throws Exception {
		if (StringUtils.isBlank(encode)) {
			encode = "utf-8";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (bit == 16)
				return bytesToHex(md.digest(input.getBytes(encode))).substring(8, 24);
			return bytesToHex(md.digest(input.getBytes(encode)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}

	public static String md5_3(String b) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] a = md.digest(b.getBytes());
		a = md.digest(a);
		a = md.digest(a);

		return bytesToHex(a);
	}

	/**
	 * md5㷨
	 * 
	 * @param text
	 * @return
	 */
	public static String md5Excep(String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		try {
			msgDigest.update(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("System doesn't support your  EncodingException.");
		}
		byte[] bytes = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = hexDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = hexDigits[0x0F & data[i]];
		}
		return out;
	}
}
