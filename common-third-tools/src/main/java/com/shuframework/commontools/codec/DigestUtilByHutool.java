package com.shuframework.commontools.codec;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 不可逆加密工具类（推荐线上使用）<br>
 * 使用的是hutool-crypto.jar来统一实现加密操作。
 * md5、sha加密底层是java.security.MessageDigest
 *
 * @author shuheng
 */
public class DigestUtilByHutool {

	/**
	 * md5 加密。返回16进制的字符串,长度32位（不可逆）
	 * @param str
	 * @return
	 */
	public static String md5Hex(String str) {
		return DigestUtil.md5Hex(str);
	}
	public static String md5Hex(byte[] data) {
		return DigestUtil.md5Hex(data);
	}
	
	/**
	 * sha1 加密。返回16进制的字符串,长度40位（不可逆）
	 * @param str
	 * @return
	 */
	public static String sha1Hex(String str) {
		return DigestUtil.sha1Hex(str);
	}
	public static String sha1Hex(byte[] data) {
		return DigestUtil.sha1Hex(data);
	}
	
	/**
	 * sha256 加密。返回16进制的字符串,长度64位（不可逆）
	 * @param str
	 * @return
	 */
	public static String sha256Hex(String str) {
		return DigestUtil.sha256Hex(str);
	}
	public static String sha256Hex(byte[] data) {
		return DigestUtil.sha256Hex(data);
	}
}
