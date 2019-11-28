package com.shuframework.commontools.codec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 不可逆加密工具类（推荐线上使用）<br>
 * org.springframework.util.DigestUtils 工具类功能太单一，
 * 使用的是commons-codec.jar来统一实现加密操作。
 *
 * @author shuheng
 */
public class DigestUtilByCommon {

	/**
	 * md5 加密。返回16进制的字符串,长度32位（不可逆）
	 * @param str
	 * @return
	 */
	public static String md5Hex(String str) {
		return DigestUtils.md5Hex(str);
	}
	public static String md5Hex(byte[] data) {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * sha1 加密。返回16进制的字符串,长度40位（不可逆）
	 * @param str
	 * @return
	 */
	public static String sha1Hex(String str) {
		return DigestUtils.sha1Hex(str);
	}
	public static String sha1Hex(byte[] data) {
		return DigestUtils.sha1Hex(data);
	}
	
	/**
	 * sha256 加密。返回16进制的字符串,长度64位（不可逆）
	 * @param str
	 * @return
	 */
	public static String sha256Hex(String str) {
		return DigestUtils.sha256Hex(str);
	}
	public static String sha256Hex(byte[] data) {
		return DigestUtils.sha256Hex(data);
	}
}
