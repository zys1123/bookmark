package com.bookmark.utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Util {

		/**
		 * ʹMD5加密方法
		 */
		public static String md5(String plainText) {
			byte[] secretBytes = null;
			try {
				secretBytes = MessageDigest.getInstance("md5").digest(
						plainText.getBytes());
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("没有找到对应的加密方法");
			}
			String md5code = new BigInteger(1, secretBytes).toString(16);// 16��������
			for (int i = 0; i < 32 - md5code.length(); i++) {
				md5code = "0" + md5code;
			}
			return md5code;
		}

		public static void main(String[] args) {
			System.out.println(md5("i"));
		}

}

