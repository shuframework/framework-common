package com.shuframework.commontools.jmail;

import org.junit.Test;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSendUtilTest {

	@Test
	public void simpleSend_test1() {
		MailSendInfo mailInfo = initMailSendInfo();
		boolean flag = MailSendUtil.simpleSend(mailInfo);
		System.out.println(flag);
	}

	@Test
	public void sendHasFile_test2() {
		MailSendInfo mailInfo = initMailSendInfo();
		
		String filePathPrefix = "D:/autotemp/";
		String fileName1 = "users.xls";
		String fileSource1 = filePathPrefix + fileName1;
		String fileName2 = "waybills.xlsx";
		String fileSource2 = filePathPrefix + fileName1;
		AttachBean attachBean1 = new AttachBean(fileName1, fileSource1);
		AttachBean attachBean2 = new AttachBean(fileName2, fileSource2);
		
		//添加附件
		mailInfo.addAttach(attachBean1);
		mailInfo.addAttach(attachBean2);
		
		boolean flag = MailSendUtil.sendHasFile(mailInfo);
		System.out.println(flag);
	}

	private MailSendInfo initMailSendInfo() {
		String username = "shu971322430@163.com";
		String password = "xxxxxxxxx";//username的邮件对应的密码
		//mailServerHost 与fromAddress 对应，与是163协议，则发件人只能是163的
		String mailServerHost = SMTPEnum.WY_163.getHost();
		String fromAddress = "shu971322430@163.com";

//		String toAddress = "971322430@qq.com"; //单发
		String toAddress = "971322430@qq.com, shuheng@bmkp.cn";//群发
//		String ccAddress = "heng.shu@winphone.us";
		String subject = "会议20170117";
		String content = "shu971322430@163.com发送以下内容看";

		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setUsername(username);
		mailInfo.setPassword(password);
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setFrom(fromAddress);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		mailInfo.addToAddress(toAddress);
//		mail.addCcAddress(ccAddress);
		mailInfo.addCcAddress(null);
		return mailInfo;
	}

}
