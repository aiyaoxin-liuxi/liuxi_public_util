package com.liuxi.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 简单邮件发送
 * @author 
 * @version 1.0 2014/05/25
 */
public class Mail {
	/**
	 * @param username  用户名，一般都是邮箱账户
	 * @Param password  密码
	 * @param fromAddress 发件人
	 * @param toAddress 收件人，可群发
	 * @param mailServerHost 邮箱服务器ip
	 * @param mailServerPort 端口
	 * @param subject   邮件标题
	 * @param content   邮件内容
	 */
	private String username;
	private String password;
	private String fromAddress;
	private String [] toAddress;
	private String mailServerHost;
	private Integer mailServerPort;
	private String subject;
	private String content;
	
	//配置
	private Properties props = new Properties();
	//环境
	private Session session;
	//发送器
	private Transport transport;
	//邮件
	private Message msg;
	
	/**
	 * 配置信息
	 */
	private void setInfo(){
		//登入邮箱服务器是需要验证的
		props.setProperty("mail.smtp.auth", "true");
		//设置协议
		props.setProperty("mail.transport.protocol", "smtp");
	}
	
	/**
	 * 设置信件信息
	 */
	private void setMessage(){
		//环境信息
		session = Session.getDefaultInstance(this.props);
		//创建邮件信息
		msg = new MimeMessage(session);
		try {
			//设置邮件标题
			msg.setSubject(this.subject);
			//设置邮件内容
			msg.setText(content);
			//设置邮件发信人
			msg.setFrom(new InternetAddress(this.username));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到收件人的一个数组，满足群发
	 * @return Address[]  收件人数组
	 */
	private Address [] getAddress() {
		Address [] address = new Address[this.toAddress.length];
		for(int i = 0; i < this.toAddress.length;i++){
			try {
				address[i] = new InternetAddress(this.toAddress[i]);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return address;
	}
	
	/**
	 * 给用户只暴露这个方法
	 * 通过这个方法，对信息进行设置，然后再发送
	 */
	public void send(){
		setInfo();
		setMessage();
		try {
			//创建一个发送器transport用来发送邮件内容
			transport = session.getTransport();
			transport.connect(this.mailServerHost, this.mailServerPort, this.username, this.password);
			//后面的邮件是受件人的邮箱，可以指定多个
			transport.sendMessage(this.msg, this.getAddress());	
			transport.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public Integer getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(Integer mailServerPort) {
		this.mailServerPort = mailServerPort;
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
	
}