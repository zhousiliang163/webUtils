package com.util.sms;

import java.util.Map;

import org.apache.log4j.Logger;

import com.java.sms.client.JMSUtil;

public class ReceiveMessage extends Thread {
	private  String password="11111111111111111111111111111113";
	Logger logger=Logger.getLogger(ReceiveMessage.class);
   
	 public ReceiveMessage(String password){
		 this.password=password;
	 }
	@Override
	public void run() {
		try {
			boolean sign = true;
			while(sign){
				Map map = JMSUtil.receiveMessage(password);
				logger.info("map=="+map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
