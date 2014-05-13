package com.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;


public class StringUtils {

	/*** 成功 **/
	public static final String SUCCESS = "success";
	/*** 错误 **/
	public static final String ERROR = "error";
	/*** 失败 **/
	public static final String FAILURE = "failure";
	/*** 失败 **/
	public static final String TIP = "tip";
	/**请选择**/
	public static final String CHOOSE="--请选择--";
	

	/***************
	 * @Description: 返回一个map[100=--请选择--]
	 * @param stateNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map chooseMap(String stateNo){
		Map m=new HashMap();
		String text="";
		if(!StringUtils.isNullStr(stateNo)){
			if("100".equals(stateNo)){
				text=StringUtils.CHOOSE;
			}else{
				return null;
			}
		}else{
			return null;
		}
		m.put("id", "");
		m.put("text", text);
		return m;
	}
	/**********
	 * 
	 * @Description: TODO(封装返回map)
	 * @param state
	 *            "success"|"error"|"failure"|"tip"
	 * @param msg
	 * @return
	 */
	public static Map resultMap(String state, String msg) {
		Map map = new HashMap();
		map.put("state", state);
		map.put("msg", msg);
		return map;
	}

	public static void removeDuplicateWithOrder(ArrayList arlList) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = arlList.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
	}

	/***********
	 * 
	 * @Description: TODO(验证输入的字符串是否为空)
	 * @param str
	 * @return true=为空|false=不为空
	 */
	public static boolean isNullStr(String str) {
		if (null != str && !"".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 36进制的加法, 添加1
	 * @param value
	 * @return
	 * @author Ou
	 */
	public static String add36System(String value) {
		String KEY_SET = "0123456789abcdefghijklmnopqrstuvwxyz";
		return addStrSystem(value,KEY_SET);
	}
	/**
	 * 方法说明：x进制转10进制
	 * @param input 进制字符串
	 * @param radix 进制基数x
	 * @return
	 * @author Ou
	 * 2013-11-26
	 */
	public static int to10System(String x,int radix){
		int num = 0;
		try {
			if(StringUtils.isNotBlank(x))
				num = Integer.parseInt(x.trim(),radix);
		} catch (Exception e) {
			return 0;
		}
		return num;
	}
	/**
	 * 10进制加法，添加1
	 * @param value
	 * @return 返回value+1
	 * @author Ou
	 * 2013-3-14
	 */
	public static String add10System(String value) {
		String KEY_SET = "0123456789";
		return addStrSystem(value,KEY_SET);
	}
	/**
	 * 10进制加法，添加1
	 * @param value
	 * @return 返回value+1
	 * @author Ou
	 * 2013-3-14
	 */
	public static String addStrSystem(String value,final String KEY_SET){

		String[] bytes = value.split("");

		// 合法性校验
		for (int k = 0; k < bytes.length - 1; k++) {
			if (!"".equals(bytes[k]) && KEY_SET.indexOf(bytes[k]) == -1) {
				throw new IllegalArgumentException("错误的进制值。");
			}
		}

		boolean carryFlag = false;
		for (int k = bytes.length - 1; k >= 0; k--) {
			if (carryFlag) {
				int index = KEY_SET.indexOf(bytes[k]);
				if (index == KEY_SET.length() - 1) {// 已经是最大值，需要进位
					bytes[k] = "0";
					carryFlag = true;
					continue;
				} else {// 取下一位的值
					bytes[k] = KEY_SET.substring(index + 1, index + 2);
					carryFlag = false;
					break;
				}
			}

			// 不需要进位
			int index = KEY_SET.indexOf(bytes[k]);
			if (index == KEY_SET.length() - 1) {// 准备进制
				bytes[k] = "0";
				carryFlag = true;
			} else {
				bytes[k] = KEY_SET.substring(index + 1, index + 2);
				break;
			}
		}
		String result = "";
		for (int k = 0; k < bytes.length; k++) {
			result += bytes[k];
		}
		return result;
	}
	
	/**
	 * 返回max-min之间的随机数
	 * @param max 最大
	 * @param min 最小
	 * @return
	 * @author Ou
	 * 2013-5-6
	 */
	public static long randomNum(int max,int min){
		return Math.round(Math.random()*(max-min)+min);  //公式
	}
	/**
	 * 获取n位[0-9]string类型的随机数
	 * @param num
	 * @return
	 * @author Ou
	 * 2013-5-6
	 */
	public static String randomStr(int n){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n ; i ++){
			long num = Math.round(Math.random()*(9-0)+0);
			String[] str = {"0","1","2","3","4","5","6","7","8","9"};
			sb.append(str[(int) num]);
		}
		return sb.toString();
	}

	/**
	 * 方法说明：判空为空
	 * @param obj
	 * @return 为null或空字符串返回true
	 * Ou
	 * 2012-12-5
	 */
	public static boolean isNull(Object obj) {
		if (obj == null || "".equals(obj))
			return true;
		else
			return false;
	}
	
	/**
	 * 方法说明：判断不为空
	 * @param obj
	 * @return 为null或空字符串返回false
	 * Ou
	 * 2012-12-5
	 */
	public static boolean isNotNull(Object obj){
		if(obj == null || "".equals(obj))
			return false;
		else
			return true;
	}
	/**
	 * 方法说明：是否为空
	 * @param obj
	 * @return 为null或空字符串返回false
	 * Ou
	 * 2012-12-5
	 */
	public static String isStrNull(String str){
		if(str.equals("null")){
			str="";
			} 
		return str;
	}
	/**
	 * 生成36位的uuid
	 * @return 格式：b935ac46-b4d8-4985-bc30-d27852ef2f19
	 * @author Ou
	 * 2013-4-6
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 生成32位的uuid
	 * @return 格式：b935ac46b4d84985bc30d27852ef2f19
	 * @author Ou
	 * 2013-4-6
	 */
	public static String getUuid32(){
		return UUID.randomUUID().toString().replace("-","");
	}
	/** 
     * 以年月日时分秒毫秒+4位随机数的格式来创建一个文件名，不带扩展名 
     * @return 文件名 
     */  
    public static String createFileName() {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        //获取年月日时分秒  
        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));  
        //毫秒  
        String milli = String.valueOf(date.getTime() % 1000);  
        while (milli.length() < 3) {  
            milli = "0" + milli;  
        }  
        sb.append(milli);  
        //四位随机数  
        String rondom = String.valueOf(new Random().nextInt(10000));  
        while (rondom.length() < 4) {  
            rondom = "0" + rondom;  
        }  
        sb.append(rondom);  
        return sb.toString();  
    }
    
    public static String getNewFilePath(String currentPath,String fileName){
    	//临时目录,用于生成临时文件  
        String tempPath = "" ; 
    	File f = new File(currentPath);  
        //不存在则创建它  
        if (!f.exists())   
            f.mkdirs(); 
        tempPath = currentPath + "/" + fileName;  
        return tempPath;
    }
    /**
     * 替换从word等文件中复制带有tab格式的字符，在字符中表现为"\t"
     * @param str
     * @return
     * Ou
     * 2013-7-26
     */
    public static String replaceTab(String str){
    	return str.replaceAll("\t", "");
    }
	/**
	 * 判断字符串是否为空的字符串,即没有任何字符
	 * <p>
	 * 例如:isEmpty("")则返回true,isEmpty(" ")则返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 判断字符串是否为非空的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断字符串是否为一个空格或者多个空格字符串
	 * <p>
	 * 例如:isBlank("")则返回true,isBlank(" ")则返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断字符串不为一个空格或者多个空格字符串
	 * <p>
	 * 查看isBlank(String str)方法
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 比较两个字符值是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean eqauls(String str1,
			String str2) {
		return (str1 == str2 || (str1 != null && str1.equals(str2)));
	}

	/**
	 * toString
	 * 
	 * @param i
	 * @return
	 */
	public static String toString(int i) {
		return i + "";// new Integer(i).toString();
	}

	/**
	 * toString
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	/**
	 * 去除字符串两边的空格
	 * <p>
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str != null) {
			str = str.trim();
		}
		return str;
	}

	/**
	 * 若该斯字符串为空白字符，则返回空。
	 * 
	 * @param str
	 * @return
	 */
	public static String returnNullIfBlank(String str) {
		if (isBlank(str)) {
			return null;
		}
		return str;
	}

	/**
	 * 若该对象为NULL，则返回Empty。即""的字符串。
	 * 
	 * @param object
	 * @return
	 */
	public static String returnEmptyIfNull(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * 若该斯字符串为空字符，则返回空。
	 * 
	 * @param str
	 * @return
	 */
	public static String returnNullIfEmpty(String str) {
		if (isEmpty(str)) {
			return null;
		}
		return str;
	}
	/**
	 * 把字符数组连接成字符串，以"，"连接
	 * 
	 * @param stringArray
	 * @return
	 */
	public static String arrayToString(String[] stringArray) {
		return arrayToString(stringArray, ",");
	}

	/**
	 * 把字符数组连接成字符串
	 * 
	 * @param stringArray
	 * @param joinchar
	 *            连接字符串
	 * @return
	 */
	public static String arrayToString(String[] stringArray,
			String joinchar) {
		if (stringArray == null || stringArray.length == 0) {
			return "";
		}

		if (joinchar == null) {
			joinchar = ",";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stringArray.length; i++) {
			sb.append(stringArray[i]).append(joinchar);
		}

		sb.delete(sb.length() - joinchar.length(), sb.length());

		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String[] strToArrTo(String src,String delim){
		StringTokenizer s=new StringTokenizer(src,delim);
		List list=new ArrayList();
		while(s.hasMoreElements()){
			list.add((String)s.nextElement());
		}
		Object obj[]=list.toArray();
		String[] arr=new String[obj.length];
		for(int i=0;i<obj.length;i++){
			arr[i]=(String)obj[i];
		}
		return arr;
	}
	
	/**********************
	 * @Description: 根据给定的分隔符把字符串转为List<String>
	 * @param src
	 * @param delim
	 * @return
	 */
	public static List<String> strToList(String src,String delim){
		StringTokenizer s=new StringTokenizer(src,delim);
		List<String> list=new ArrayList<String>();
		while(s.hasMoreElements()){
			list.add((String)s.nextElement());
		}
		return list;
	}
	
	/**************
	 * list集合转为字符串根据给定的分隔符
	 * 描述  
	 * @param list
	 * @param delim 分隔符
	 * @return
	 */
	public static String listToStr(List<String> list,String delim){
		StringBuffer buf=new StringBuffer();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				buf.append(list.get(i)+delim);
			}
			buf.delete(buf.length()-1, buf.length());
		}
		return buf.toString();
	}
	
	public static void main(String[] args) {
		String subid="[0f0a01,0f0a02]";
		StringBuffer sb=new StringBuffer(subid);
		sb.delete(0, 1).delete(sb.length()-1, sb.length());
		String[] sub=StringUtils.strToArrTo(sb.toString(), ",");
		for(String s:sub){
			System.out.println(s);
		}
	}
}
