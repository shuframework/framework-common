package com.shuframework.commontools.codec;

import org.junit.Test;

public class DigestUtilTest {

	@Test
	public void encodeBase64_test() {
		String str = "test";
		String base64Str = Base64Util.encodeBase64(str);
		//dGVzdA==
		System.out.println(base64Str);
	}
	
	@Test
	public void decodeBase64_test() {
		String str = "dGVzdA==";
		String base64Str = Base64Util.decodeBase64(str);
		//test
		System.out.println(base64Str);
	}
	
	@Test
	public void md5_test() {
		String str = "test";
		String md5Str = DigestUtilByCommon.md5Hex(str);
		//32位,098f6bcd4621d373cade4e832627b4f6
		System.out.println(md5Str);
		String md5Str2 = DigestUtilByHutool.md5Hex(str);
		System.out.println(md5Str2);
	}
	
	@Test
	public void sha1_test() {
		String str = "test";
		String sha1Str = DigestUtilByCommon.sha1Hex(str);
		//40位,a94a8fe5ccb19ba61c4c0873d391e987982fbbd3
		System.out.println(sha1Str);
		String sha1Str2 = DigestUtilByHutool.sha1Hex(str);
		System.out.println(sha1Str2);
	}
	
	@Test
	public void sha256_test() {
		String str = "test";
		String sha256Str = DigestUtilByCommon.sha256Hex(str);
		//64位,9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08
		System.out.println(sha256Str);
		String sha256Str2 = DigestUtilByCommon.sha256Hex(str);
		System.out.println(sha256Str2);
	}

}
