package com.shuframework.commontools.jmail;

import java.io.File;

/**
 * mail 附件bean
 * @author shuheng
 *
 */
public class AttachBean {
	/** 发送邮件的服务器*/
	private String cid;
	/** 发送邮件的服务器*/
	private File file;
	/** 邮件附件名*/
	private String fileName;
	/** 邮件附件绝对路径*/
	private String fileSource;

	public AttachBean() {}

	public AttachBean(File file, String fileName) {
		this.file = file;
		this.fileName = fileName;
	}
	
	public AttachBean(String fileName, String fileSource) {
		this.fileName = fileName;
		this.fileSource = fileSource;
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

}
