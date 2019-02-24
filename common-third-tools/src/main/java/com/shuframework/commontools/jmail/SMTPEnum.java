package com.shuframework.commontools.jmail;

/**
 * smtp(简单邮件传输协议)的枚举
 * @author shuheng
 */
public enum SMTPEnum {
	//smtp协议地址详情  https://www.cnblogs.com/shangdawei/p/4305989.html

	QQ("smtp.qq.com", "腾讯qq邮箱服务器"),
	SINA("smtp.sina.com", "新浪邮箱服务器"),
	SINA_VIP("smtp.vip.sina.com", "新浪vip邮箱服务器"),
	SOHU("smtp.sohu.com", "搜狐邮箱服务器"),
	ALIYUN("smtp.aliyun.com", "阿里云邮箱服务器"),
	
	WY_126("smtp.126.com", "网易126邮箱服务器"),
	WY_163_VIP("smtp.vip.163.com", "网易vip163邮箱服务器"),
	WY_163("smtp.163.com", "网易163邮箱服务器");
	
	private String host;
	private String desc;
	
	SMTPEnum(String host, String desc) {
		this.host = host;
		this.desc = desc;
	}

	public String getHost() {
		return host;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
