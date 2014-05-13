package com.util;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Spring;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;


/**
 * Hibernate工具类 
 * @author yaha
 *
 */
public class HibernateUtil {
	private static SessionFactory _factory;
	
	public static SessionFactory getSessionFactory(){
		
		if (_factory == null) {
			return (SessionFactory)SpringHelper.getBean("sessionFactory");
		}
		return _factory;
	}
	
	/**
	 * 获取实体某个属性在相应数据库表中对应的字段名
	 * @param entityClass 实体类型
	 * @param fieldName  属性名
	 * @return
	 */
	public static String getColumnNameByFieldName(Class<?> entityClass,String fieldName) {
		String ret = null;
		if(entityClass != null) {
			try{
				SessionFactory factory = getSessionFactory();
				AbstractEntityPersister classMetadata = (AbstractEntityPersister)factory.getClassMetadata(entityClass);
				ret = classMetadata.getPropertyColumnNames(fieldName)[0];
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	
	/**
	 * 获取实体类映射表的字段名数组
	 * @param entityClass
	 * @return
	 */
	public static String[] getPropertyNames(Class<?> entityClass) {
		String[] ret = new String[]{};
		if(entityClass != null) {
			try{
				SessionFactory factory = getSessionFactory();
				ClassMetadata classMetadata = factory.getClassMetadata(entityClass);
				String[] propertys = classMetadata.getPropertyNames(); //获取普通字段
				String identifier =  classMetadata.getIdentifierPropertyName();  //获取主键
				ret = new String[1 +propertys.length];
				ret[0] = identifier;
				System.arraycopy(propertys, 0, ret, 1, propertys.length);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * 得到实体属性所字段的类型码
	 * @param entityClass
	 * @param propertyName
	 * @return 类型码(参见：java.sql.Types)
	 */
	public static Integer getColumnTypeNumByPropertyName(final Class<?> entityClass, final String propertyName) {
		return getColumnTypeNumByColumnName(entityClass, HibernateUtil.getColumnNameByFieldName(entityClass, propertyName));
	}
	
	/**
	 * 得到字段的类型码
	 * @param entityClass
	 * @param columnName
	 * @return 类型码(参见：java.sql.Types)
	 */
	public static Integer getColumnTypeNumByColumnName(final Class<?> entityClass, final String columnName) {
		final List<Integer> ret = new ArrayList<Integer>();
		SessionFactory factory = getSessionFactory();
		factory.openSession().doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				Statement stmt = null;
				ResultSetMetaData rsmd = null;
				stmt = conn.createStatement();
				rsmd = stmt.executeQuery("select " + columnName + " from " + getTableNameByClass(entityClass)).getMetaData();
				try {
					ret.add(rsmd.getColumnType(1));
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(stmt != null) {
							stmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		return ret.isEmpty() ? null : ret.get(0);
	}
	
	/**
	 * 获取实体类的数据库表名
	 * @param entityClass
	 * @return
	 */
	public static String getFieldNameByColumnName(Class<?> entityClass,String columnName) {
		String ret = null;
		if(entityClass != null) {
			try{
				SessionFactory factory = getSessionFactory();
				AbstractEntityPersister classMetadata = (AbstractEntityPersister)factory.getClassMetadata(entityClass);
				String[] props = classMetadata.getPropertyNames();
				start : {
					for(String prop : props) {
						for(String column : classMetadata.getPropertyColumnNames(prop)) {
							if(column.equalsIgnoreCase(columnName)) {
								ret = prop;
								break start;
							}
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	/**
	 * 获取实体类的数据库表名
	 * @param entityClass
	 * @return
	 */
	public static String getTableNameByClass(Class<?> entityClass) {
		String ret = null;
		if(entityClass != null) {
			try{
				SessionFactory factory = getSessionFactory();
				AbstractEntityPersister classMetadata = (AbstractEntityPersister)factory.getClassMetadata(entityClass);
				ret = classMetadata.getTableName();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * 通过表名获取对应的实体类类型
	 * @param tableName
	 * @return
	 */
	public static Class<?> getEntityClassByTableName(String tableName) {
		Class<?> ret = null;
		if(!StringUtils.isBlank(tableName)) {
			try{
				SessionFactory factory = getSessionFactory();
				Map<String,ClassMetadata> metaMap = factory.getAllClassMetadata();
				for (String key : (Set<String>) metaMap.keySet()) {
					AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
					if(classMetadata.getTableName().equalsIgnoreCase(tableName)) {
						ret = classMetadata.getMappedClass();
						break;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
        return ret;
	}
	
	/**
	 * 把字符串型的值组装成实体属性
	 * @param entityClass
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static Object getPropertyValue(Class<?> entityClass, String propertyName,String propertyValueStr) {
		Object ret = null;
		Integer typeNum = getColumnTypeNumByPropertyName(entityClass, propertyName);
		switch(typeNum) {
		case Types.VARCHAR :
			ret = propertyValueStr;
			break;
		case Types.INTEGER :
			ret = Integer.valueOf(propertyValueStr);
			break;
		case Types.BIGINT :
			ret = Long.valueOf(propertyValueStr);
			break;
		case Types.DOUBLE :
			ret = Double.valueOf(propertyValueStr);
			break;
		case Types.FLOAT :
			ret = Float.valueOf(propertyValueStr);
			break;
		case Types.DATE :
		case Types.TIME :
		case Types.TIMESTAMP:
			SimpleDateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				ret = dateFormat.parse(propertyValueStr);
			} catch (ParseException e) {
				System.out.println("字符串转换成日期出错，字符串格式须为：yyyy-MM-dd HH:mm:ss");
			}
			break;
		case Types.BIT :
			ret = ("true".equalsIgnoreCase(propertyValueStr) || "yes".equalsIgnoreCase(propertyValueStr)) ? Boolean.TRUE : Boolean.FALSE;
			break;
		}
		return ret;
	}
	
}

