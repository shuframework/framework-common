package com.shuframework.commontools.jmail;

import com.shuframework.commonbase.util.SystemUtil;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


/**
 * java 邮件发送工具类
 * javax.mail.Session：上下文环境信息，如服务器的主机名、端口号、协议名称等  
 * javax.mail.Message：邮件模型，发送邮件和接收邮件的媒介，封装了邮件的信息，如发件人、收件人、邮件标题、邮件内容等  
 * javax.mail.Transport：连接邮件SMTP服务器，发送邮件  
 * javax.mail.Store：连接邮件POP3、IMAP服务器，收取邮件  
 *
 * @author shuheng
 */
public class MailSendUtil {

	private static final String TYPE_HTML_UTF8 = "text/html;charset=utf-8";
	
	/**
	 * 简单内容 发送(支持多个收件人、抄送人、暗送人)
	 * 
	 * @param mailInfo
	 */
	public static boolean simpleSend(MailSendInfo mailInfo) {
		boolean flag = false;
		try {
			//创建消息
			MimeMessage msg = initMsg(mailInfo);
			msg.setContent(mailInfo.getContent(), TYPE_HTML_UTF8);
			
			//发送
			Transport.send(msg);
			flag = true;
			
		} catch (MessagingException e) {
	        e.printStackTrace();  
		}
		
		return flag;
	}
	
	/**
	 * 含附件的发送
	 * 
	 * @param mailInfo
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static boolean sendHasFile(MailSendInfo mailInfo) {
		boolean flag = false;
		try {
			//创建消息
			MimeMessage msg = initMsg(mailInfo);
			
			MimeMultipart parts = new MimeMultipart();
			MimeBodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(mailInfo.getContent(), TYPE_HTML_UTF8);
			parts.addBodyPart(bodypart);
			
			List<AttachBean> attachBeanList = mailInfo.getAttachs();
			if (attachBeanList != null) {
				MimeBodyPart attachPart;
				for (AttachBean attachBean : attachBeanList) {
					attachPart = new MimeBodyPart();
					attachPart.setDataHandler(new DataHandler(new FileDataSource(attachBean.getFileSource())));  
					//MimeUtility.encodeText 是对fileName 进行加密
					attachPart.setFileName(MimeUtility.encodeText(attachBean.getFileName()));
//					//第二种方式
//					attachPart.attachFile(attachBean.getFile());
//					String cid = attachBean.getCid();
//					if (cid != null){
//						attachPart.setContentID(cid);
//					}
					parts.addBodyPart(attachPart);
				}
			}
			msg.setContent(parts);
			
			//发送
			Transport.send(msg);
			flag = true;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 创建邮件连接对象
	 * @param mailInfo
	 */
	private static Session createSession(final MailSendInfo mailInfo) {
		//内部类实现, 减少MyAuthenticator类的定义
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailInfo.getUsername(), mailInfo.getPassword());
			}
		};
		return Session.getInstance(mailInfo.getProperties(), auth);
		//实际项目还是使用getInstance, 避免getDefaultInstance单例带来的问题
//		return Session.getDefaultInstance(mailInfo.getProperties(), auth);
		/*
		 * getInstance 与 getDefaultInstance的区别
		 * getDefaultInstance是真正单例模式，而且里面的username和password属性是final型的，无法更改。
		 * 当一个应用为每个用户独立创建properties的时候，还是应该调用getInstance
		 * 如果是想同时使用两个帐号发送javamail，还是应该调用getInstance
		 */
	}

	/**
	 * 创建消息
	 * @param mailInfo
	 * @throws MessagingException
	 * @throws AddressException
	 */
	@SuppressWarnings("static-access")
	private static MimeMessage initMsg(MailSendInfo mailInfo) throws MessagingException {
		Session session = createSession(mailInfo);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mailInfo.getFrom()));
		//添加收件人
		msg.addRecipients(Message.RecipientType.TO,  new InternetAddress().parse(mailInfo.getToAddress()));//多个
//		msg.addRecipients(Message.RecipientType.TO, mail.getToAddress());//单个
		String cc = mailInfo.getCcAddress();
		if (SystemUtil.isNotEmpty(cc)){
//		if (SystemUtil.isNotEmpty(cc)){
			//添加抄送人
			msg.addRecipients(Message.RecipientType.CC, new InternetAddress().parse(cc));//多个
//			msg.addRecipients(Message.RecipientType.CC, cc);//单个
		}
		String bcc = mailInfo.getBccAddress();
		if (SystemUtil.isNotEmpty(bcc)){
			//添加 密送人（暗抄送）
			msg.addRecipients(Message.RecipientType.BCC, new InternetAddress().parse(bcc));//多个
//			msg.addRecipients(Message.RecipientType.BCC, bcc);//单个
		}
		msg.setSubject(mailInfo.getSubject());
		msg.setSentDate(new Date());
		return msg;
	}

}
