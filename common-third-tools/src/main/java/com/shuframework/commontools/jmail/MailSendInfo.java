package com.shuframework.commontools.jmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 邮件bean，包含发送，抄送，暗送，邮件标题，内容等
 * @author shuheng
 */
public class MailSendInfo {
	/** 发送邮件的服务器主机*/
	private String mailServerHost;
//	/** 发送邮件的服务器端口*/
//	private String mailServerPort;//SMTP协议默认端口,值是25
	
	/** 登陆邮件发送服务器的用户名 */
	private String username;
	/** 登陆邮件发送服务器的密码 */
	private String password;
	/** 发件人邮箱地址 */
	private String from;
	/** 收件人邮箱地址, 多个用逗号隔开 */
	private StringBuilder toAddress;
	/** 抄送人邮箱地址, 多个用逗号隔开 */
	private StringBuilder ccAddress;
	/** 密送人（暗抄送）邮箱地址, 多个用逗号隔开 */
	private StringBuilder bccAddress;
	
	/** 邮件主题 */
	private String subject;
	/** 邮件的文本内容 */
	private String content;
	/** 邮件附件的文件名 */
	private List<AttachBean> attachList;

	/**
	 * 邮件的服务器信息
	 * @return
	 */
	public Properties getProperties() {  
		  Properties p = new Properties();  
		  p.put("mail.smtp.host", this.mailServerHost);  
//		  p.put("mail.smtp.port", this.mailServerPort);//SMTP协议默认端口,值是25
		  p.put("mail.smtp.auth", "true");  
		  return p; 
	 }
	
	public MailSendInfo() {
		toAddress = new StringBuilder();
		ccAddress = new StringBuilder();
		bccAddress = new StringBuilder();
		attachList = new ArrayList<>();
	}

	public MailSendInfo(String from, String to) {
		this(from, to, null, null);
	}

	public MailSendInfo(String fromAddress, String to, String subject, String content) {
		toAddress = new StringBuilder();
		ccAddress = new StringBuilder();
		bccAddress = new StringBuilder();
		attachList = new ArrayList<>();
		toAddress.append(to);
		this.from = fromAddress;
		this.subject = subject;
		this.content = content;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToAddress() {
		return toAddress.toString();
	}

	public String getCcAddress() {
		return ccAddress.toString();
	}

	public String getBccAddress() {
		return bccAddress.toString();
	}

	/**
	 * toAddress赋值
	 * @param to
	 */
	public void addToAddress(String to) {
		if (toAddress.length() > 0)
			toAddress.append(",");
		
		if (null != to){
			toAddress.append(to);
		}
	}

	/**
	 * ccAddress赋值
	 * @param cc
	 */
	public void addCcAddress(String cc) {
		if (ccAddress.length() > 0){
			ccAddress.append(",");
		}
		
		if (null != cc){
			ccAddress.append(cc);
		}
	}

	/**
	 * bccAddress赋值
	 * @param bcc
	 */
	public void addBccAddress(String bcc) {
		if (bccAddress.length() > 0){
			bccAddress.append(",");
		}
			
		if (null != bcc){
			bccAddress.append(bcc);
		}
	}

	public void addAttach(AttachBean attachBean) {
		attachList.add(attachBean);
	}

	public List<AttachBean> getAttachs() {
		return attachList;
	}
}
