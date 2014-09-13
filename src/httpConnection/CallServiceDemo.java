package httpConnection;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CallServiceDemo {
	
	public static void main(String[] args) throws Exception {
		String str="hksmx2:ijkuy2";//这里写用户名加密码
		String str_64=new BASE64Encoder().encode(str.getBytes());
		System.out.println(str_64);
		
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
        
		URL u = null;
		HttpsURLConnection con = null;
		
		u = new URL("https://211.147.237.42:8043/batch/verify");
		con = (HttpsURLConnection) u.openConnection();
		con.setSSLSocketFactory(sc.getSocketFactory());
		con.setHostnameVerifier(new TrustAnyHostnameVerifier());
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(false);
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization", "Basic " +str_64);   
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write("");//这里写json字符串
		osw.flush();
		osw.close();
		
		StringBuffer result = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			result.append(temp);
		}
		System.out.println(result.toString());
		
	}
	
	

}
