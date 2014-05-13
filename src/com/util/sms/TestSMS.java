package com.util.sms;

import org.apache.log4j.PropertyConfigurator;

public class TestSMS {
  public static void main(String[] args) {
	/*PropertyConfigurator.configure("src/com/util/sms/log4j.properties");
	ReceiveMessage receiveMessage=new ReceiveMessage();
	receiveMessage.start();
	
	IsCloseReceiveConn isCloseReceiveConn=new IsCloseReceiveConn();
	isCloseReceiveConn.start();*/
	
	  
	  
	  
		/**
		 * 短信发送

		Parameters:
				receives receives=xxxxxxxxxx;接收者的32位字符串标识，必须包含在map中。多个接收者标识用”;”隔开，如出现错误的标识，短信会提交失败。由短信平台提供标识和人名的对应关系，即通讯录。接收者必须在短信平台提供的通讯录范围内。
				content content=短信的内容，必须包含在map中。短信的内容必须匹配短信平台提供的短信模板。短信模板由第三方系统相关人员和短信平台管理员共同确认。
				smsId smsId=短信标识;需要回复的短信加上这个参数，回复的短信会包含smsId送到第三系统用于匹配发送的短信。
				sendTime sendTime可以输入也可以不输入，输入时格式必须与cycle匹配。
				cycle cycle可以不输入或输入-1(立即发送短信)、0(定时发送短信)、1(年为周期循环)、2(月为周期循环)、3(周围周期循环)、4(日为周期循环)。
				title title=短信主题;可以不加入map。
				end end=扩展字段;可以不加入map。
		Returns:
		返回参数：一个boolean类型。 返回true表示短信提交到短信平台；返回false或抛出异常表示短信没有提交到短信平台。  
		 */  
	String password="";
	SmsManager smsManager=new SmsManager(password);
	String receives="18928720822";
	String content ="测试模板1";
	String smsId="";
	String sendTime="";
	String cycle="";
	String title="";
	String end ="";
	smsManager.sendMessage(receives, content, smsId, sendTime, cycle, title, end);
	
	
	
	
 }
  
}
