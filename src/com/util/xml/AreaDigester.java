package com.util.xml;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;


public class AreaDigester {
    
    public ViewCache digester() throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
       
        Xml xml=new Xml();
        digester.push(xml);
        
        digester.addObjectCreate("viewcache", ViewCache.class);
        digester.addSetNext("viewcache", "setCache");
		// 指明匹配模式和要创建的类 
        digester.addObjectCreate("viewcache/areas/area", Area.class);
		// 设置对象属性,与xml文件对应,不设置则是默认
        digester.addBeanPropertySetter("viewcache/areas/area/id", "id");
        digester.addBeanPropertySetter("viewcache/areas/area/parentId", "parentId");
        digester.addBeanPropertySetter("viewcache/areas/area/name", "name");
        digester.addBeanPropertySetter("viewcache/areas/area/areaType", "areaType");
        digester.addBeanPropertySetter("viewcache/areas/area/ordering", "ordering");
        digester.addBeanPropertySetter("viewcache/areas/area/zip", "zip");
        digester.addBeanPropertySetter("viewcache/areas/area/phoneArea", "phoneArea");
		// 当移动到下一个标签中时的动作
        digester.addSetNext("viewcache/areas/area", "addArea");
        
       
        
        ViewCache vc = null;
        try {
            vc = (ViewCache) digester.parse(new File("D:\\Workspaces\\MyEclipse8.6\\webUtils\\src\\com\\util\\xml\\viewcache.xml"));
            
        } catch (IOException e) {
            throw new Exception(e);
        } catch (SAXException e) {
            throw new Exception(e);
        }
        return vc;
    }
    
    public Xml digester01() throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
       
        Xml xml=new Xml();
        digester.push(xml);
        
        digester.addObjectCreate("viewcache", ViewCache.class);
        digester.addSetNext("viewcache", "setCache");
		// 指明匹配模式和要创建的类 
        digester.addObjectCreate("viewcache/areas/area", Area.class);
		// 设置对象属性,与xml文件对应,不设置则是默认
        digester.addBeanPropertySetter("viewcache/areas/area/id", "id");
        digester.addBeanPropertySetter("viewcache/areas/area/parentId", "parentId");
        digester.addBeanPropertySetter("viewcache/areas/area/name", "name");
        digester.addBeanPropertySetter("viewcache/areas/area/areaType", "areaType");
        digester.addBeanPropertySetter("viewcache/areas/area/ordering", "ordering");
        digester.addBeanPropertySetter("viewcache/areas/area/zip", "zip");
        digester.addBeanPropertySetter("viewcache/areas/area/phoneArea", "phoneArea");
		// 当移动到下一个标签中时的动作
        digester.addSetNext("viewcache/areas/area", "addArea");
        
       
        
        Xml vc = null;
        try {
            vc = (Xml) digester.parse(new File("D:\\Workspaces\\MyEclipse8.6\\webUtils\\src\\com\\util\\xml\\viewcache.xml"));
            
        } catch (IOException e) {
            throw new Exception(e);
        } catch (SAXException e) {
            throw new Exception(e);
        }
        return vc;
    }
    
    public static void main(String[] args) throws Exception {
    	//ViewCache cache=(new AreaDigester()).digester();
    	Xml xml=(new AreaDigester()).digester01();
    	System.out.println(xml.getCache());
	}
}
