package org.learning.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private Long messageId;
	private String mesg;
	private String author;
	private Date date;

	public Message( )
	{
		
	}
	
	public Message(Long messageId, String mesg, String author) {
		this.messageId = messageId;
		this.mesg = mesg;
		this.author = author;
		this.date=new Date();
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
