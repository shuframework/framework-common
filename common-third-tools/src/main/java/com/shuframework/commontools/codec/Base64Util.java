package com.shuframework.commontools.codec;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64 可逆加密,线上可以使用jdk8的Base64
 * 用的是commons Base64实现
 *
 * @author shuheng
 *
 */
@Deprecated
public class Base64Util {
	
	/**
	 * base64加密
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str){
		byte[] b = Base64.encodeBase64(str.getBytes(), true);
		return new String(b);
	}
	
	/**
	 * base64解密
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str){
		byte[] b = Base64.decodeBase64(str.getBytes());
		return new String(b);
	}
	
}
