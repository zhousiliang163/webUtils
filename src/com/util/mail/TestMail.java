package com.util.mail;

public class TestMail
{
  public static void main(String[] args)
  {
    MailSenderInfo mailInfo = new MailSenderInfo();
    mailInfo.setMailServerHost("smtp-ent.21cn.com");
    mailInfo.setMailServerPort("11");
    mailInfo.setValidate(true);
    mailInfo.setUserName("cangguan@dhec.com.cn");
    mailInfo.setPassword("cangguan");
    mailInfo.setFromAddress("cangguan@dhec.com.cn");
    mailInfo.setToAddress("357035993@qq.com");
    mailInfo.setSubject("设置邮箱标题 如http://localhost:8080/pms/pmsIndex.action 中国桂花网");
    mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");

    String[] files = { "F:\\music\\text1.txt" };
    mailInfo.setAttachFileNames(files);
    MultiPartMailSender multiPartMailSender = new MultiPartMailSender();
    MultiPartMailSender.sendHtmlMultiPartMail(mailInfo);
  }
}