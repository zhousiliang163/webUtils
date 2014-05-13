package com.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * 绑定应用上下文，以供应用其它地方使用。<br/>
 */
public  class SpringHelper {

	/**
	 * 从spring容器中得到Bean
	 * @param beanId Spring Bean ID
	 * @return Object
	 */
	public static Object getBean(String beanId){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();   
		return wac.getBean(beanId);
	}

}
