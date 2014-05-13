package com.util.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.java.sms.client.CustomException;
import com.java.sms.client.JMSUtil;
import com.util.StringUtils;

public class SmsManager {
	private  String password="11111111111111111111111111111113";
	Logger logger=Logger.getLogger(SmsManager.class);

	 public SmsManager(String password){
		 this.password=password;
	 }
	/**
	 * 短信发送
	 * @return
	 */
   public Boolean sendMessage(){
	    Map<String,String> map = new HashMap<String,String>();
		map.put("password", password);
		map.put("receives", "13416442408");
		map.put("content", "OA系统发送测试短信---单条");
        map.put("smsId", "短信标识");
		//map.put("sendTime", "23:12:59");
		//map.put("cycle", "4");
		//map.put("response", "1");
		//map.put("title", "短信主题");
		//map.put("end", "短信末尾");
        Boolean result=false;
		try {
			result = JMSUtil.sendMessage(map);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

   }
   
   /**
    * 短信发送
    * 
    * @param params  输入参数：一个map类型。
						1.password=11111111111111111111111111111113；密码由短信平台提供的一个32位的字符串，必须包含在map中。
						2.receives=xxxxxxxxxx;接收者的32位字符串标识，必须包含在map中。多个接收者标识用”;”隔开，如出现错误的标识，短信会提交失败。由短信平台提供标识和人名的对应关系，即通讯录。接收者必须在短信平台提供的通讯录范围内。
						3.content=短信的内容，必须包含在map中。短信的内容必须匹配短信平台提供的短信模板。短信模板由第三方系统相关人员和短信平台管理员共同确认。
						4.cycle可以不输入或输入-1(立即发送短信)、0(定时发送短信)、1(年为周期循环)、2(月为周期循环)、3(周围周期循环)、4(日为周期循环)。
						5.sendTime可以输入也可以不输入，输入时格式必须与cycle匹配。
						cycle=null或""或-1,sendTime不起作用;
						cycle=0,sendTime=yyyy-MM-ddHH:mm:ss;
						cycle=1,sendTime=MM-ddHH:mm:ss;
						cycle=2,sendTime=ddHH:mm:ss;
						cycle=3,sendTime=W HH:mm:ss;
						cycle=4,sendTime=HH:mm:ss。
						6.smsId=短信标识;需要回复的短信加上这个参数，回复的短信会包含smsId送到第三系统用于匹配发送的短信。
						7.title=短信主题;可以不加入map。
						8.end=扩展字段;可以不加入map。

    * @return 返回参数：一个boolean类型。
                                     返回true表示短信提交到短信平台；返回false或抛出异常表示短信没有提交到短信平台。

    */
   public Boolean sendMessage(Map params){
	   params.put("password", password);
       Boolean result=false;
		try {
			result = JMSUtil.sendMessage(params);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

  }
   
   /**
    * 短信发送
    * 
    * @param receives receives=xxxxxxxxxx;接收者的32位字符串标识，必须包含在map中。多个接收者标识用”;”隔开，如出现错误的标识，短信会提交失败。由短信平台提供标识和人名的对应关系，即通讯录。接收者必须在短信平台提供的通讯录范围内。
    * @param content content=短信的内容，必须包含在map中。短信的内容必须匹配短信平台提供的短信模板。短信模板由第三方系统相关人员和短信平台管理员共同确认。
    * @param smsId smsId=短信标识;需要回复的短信加上这个参数，回复的短信会包含smsId送到第三系统用于匹配发送的短信。
    * @param sendTime sendTime可以输入也可以不输入，输入时格式必须与cycle匹配。
    * @param cycle cycle可以不输入或输入-1(立即发送短信)、0(定时发送短信)、1(年为周期循环)、2(月为周期循环)、3(周围周期循环)、4(日为周期循环)。
    * @param title title=短信主题;可以不加入map。
    * @param end end=扩展字段;可以不加入map。
    * @return 返回参数：一个boolean类型。
                                     返回true表示短信提交到短信平台；返回false或抛出异常表示短信没有提交到短信平台。
    */
   public Boolean sendMessage(String receives,String content,String smsId,String sendTime,String cycle,String title,String end){
	   Map<String,String> map = new HashMap<String,String>();
		map.put("password",password);
		//map.put("content", "OA系统发送测试短信---单条");
        //map.put("smsId", "短信标识");
		//map.put("sendTime", "23:12:59");
		//map.put("cycle", "4");
		//map.put("response", "1");
		//map.put("title", "短信主题");
		//map.put("end", "短信末尾");
        if (StringUtils.isNotNull(receives)) {
        	map.put("receives", receives);
		}
        if (StringUtils.isNotNull(content)) {
        	map.put("content", content);
		}
        if (StringUtils.isNotNull(smsId)) {
        	map.put("smsId", smsId);
		}
        if (StringUtils.isNotNull(sendTime)) {
        	map.put("sendTime", sendTime);
		}
        if (StringUtils.isNotNull(cycle)) {
        	map.put("cycle", cycle);
		}
        if (StringUtils.isNotNull(title)) {
        	map.put("title", title);
		}
        if (StringUtils.isNotNull(end)) {
        	map.put("end", end);
		}
       Boolean result=false;
		try {
			result = JMSUtil.sendMessage(map);
			logger.info("短信发送状态："+result);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

  }
   
   /**
    * 
    * @return
    */
   public String receiveMessage(){
	   Map<String, String> map=null;
	   try {
		   map=JMSUtil.receiveMessage(password);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   }
}
