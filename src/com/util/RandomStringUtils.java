package com.util;

/**
 * 随机字符
 */
public final class RandomStringUtils {
	public static char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
												'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'x', '0', '1',
												'2', '3', '4', '5', '6', '7', '8', '9' };

	private RandomStringUtils() {
	}

	/**
	 * 生成随机字符,包括a-z,0-9字符组成
	 * <p>
	 * 
	 * @param length
	 *            要生成随机字符的长度
	 * @return
	 */
	public static String random(int length) {
		int charsLen = chars.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(chars[(int) (Math.random() * charsLen)]);
		}
		return sb.toString();
	}
}

