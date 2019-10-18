package com.shuframework.commonbase.util;

import com.shuframework.commonbase.enums.DatePatternEnum;
import com.shuframework.commonbase.util.lang.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class SystemUtilTest {

	@Test
	public void orderId_test() {
//		for (int i = 0; i < 50; i++) {
			long orderId = SystemUtil.getOrderId();
			System.out.println(orderId);
			System.out.println(String.valueOf(orderId).length());

			String orderCode = SystemUtil.convertOrderId2Code(orderId);
			System.out.println(orderCode);
			System.out.println(orderCode.length());
//		}
	}


	@Test
	public void urlParam2Map_test() {
//		String url = "http://xxxxx?p1=v1&p2=v2";
//		Map<String, String> map = SystemUtil.urlParam2Map(url);
//		System.out.println(map);
		long workerIdBits = 5L;
		long maxWorkerId = ~(-1L << workerIdBits);
		long maxWorkerId2 = (1L << workerIdBits) - 1;
		System.out.println(maxWorkerId);
		System.out.println(maxWorkerId2);
	}
	
	@Test
	public void getNewFilename_test() {
		String oldFileName = "aa.xls";
		//17011114403356760983.xls
		String newFilename = SystemUtil.getNewFileName(oldFileName);
		System.out.println(newFilename);
	}
	
	@Test
	public void getRandomId_test() {
		//18位
		String randomId1 = SystemUtil.getRandomId();
		//20位
		String randomId2 = SystemUtil.getRandomId(8);
		
		//22位：报异常
		String randomId3 = SystemUtil.getRandomId(10);

		System.out.println(randomId1.length() + "位：" + randomId1);
		System.out.println(randomId2.length() + "位：" + randomId2);
		System.out.println(randomId3.length() + "位：" + randomId3);
	}
	

	@Test
	public void username_test() {
		String phone = "13986109774";
		String str = phone.substring(phone.length() - 4);
		String dateToStr = DateUtil.format(new Date(), DatePatternEnum.YMD_.getCode());
		String verifycode = SystemUtil.verifyCodeHasLetter();
//		System.out.println(verifycode);

		String code = dateToStr + str + verifycode;
		System.out.println(code);
		System.out.println(code.length());
	}

	@Test
	public void verifycode_test() {
		for (int i = 0; i < 50; i++) {
			Integer verifycode = SystemUtil.verifyCode();
			System.out.println(verifycode);
		}
	}

	@Test
	public void verifyCodeHasLetter_test() {
		for (int i = 0; i < 50; i++) {
			String verifycode = SystemUtil.verifyCodeHasLetter();
			System.out.println(verifycode);
		}
	}
	
}
