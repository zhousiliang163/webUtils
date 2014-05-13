package com.util.sms;

import java.util.Map;

import org.apache.log4j.Logger;

import com.java.sms.client.CustomException;
import com.java.sms.client.JMSUtil;
import com.java.sms.client.MessageProcess;

public class IsCloseReceiveConn extends Thread {
	private  String password="11111111111111111111111111111113";
	Logger logger=Logger.getLogger(ReceiveMessage.class);
	
	public IsCloseReceiveConn(String password){
		this.password=password;
	}
	
	@Override
	public void run() {
			while(true){
					try {
						Thread.sleep(1000*60*30);
					   if(JMSUtil.isCloseReceiveConn()){
							JMSUtil.receiveMessageListener(password, new MessageProcess() {
								@Override
								public void execute(Map<String, String> arg0) {
									logger.info("网络异常而接收到的短信："+arg0);
								}
							});
					    }
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (CustomException e) {
						e.printStackTrace();
					}
			 }

	}

}
