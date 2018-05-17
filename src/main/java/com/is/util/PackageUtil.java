package com.is.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.http.codec.multipart.SynchronossPartHttpMessageReader;

import com.is.entity.TUser;
import com.is.json.entty.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PackageUtil {
	
	/**
	 * 将fromObject对象里面的数据封装到desObject对象里面去
	 * @param desObject
	 * 为被封装的对象
	 * @param fromObject
	 * 为 数据来源
	 */
	public static<T> void packageObject(T desObject,T fromObject) {
		Class<? extends Object> desClazz = desObject.getClass();
		Class<? extends Object> fromClazz = fromObject.getClass();
		Field[] desFields = desClazz.getDeclaredFields();
		for(Field field : desFields) {
			Field fromField = null;
			try {
				String setMethodName = getSetMethodName(field.getName());
				Method setMethod = desClazz.getMethod(setMethodName, field.getType());
				
				String getMethodName = getGetMethodName(field.getName());
				Method getMethod = fromClazz.getMethod(getMethodName);
				
				setMethod.invoke(desObject, getMethod.invoke(fromObject));
			} catch (NoSuchFieldException e) {
				log.error("打包异常NoSuchFieldException  " + e.getMessage());
				continue;
			} catch (SecurityException e) {
				log.error("打包异常SecurityException  " + e.getMessage());
				continue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("打包异常SecurityException  " + e.getMessage());
				continue;
			}
			
			
		}
		
		
	}
	
	private static String getSetMethodName(String filedName) throws Exception {
		if(filedName == null || filedName.trim().equals(""))
			throw new Exception("filedName为空或没有");
		
		return "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
	}
	
	private static String getGetMethodName(String filedName) throws Exception {
		if(filedName == null || filedName.trim().equals(""))
			throw new Exception("filedName为空或没有");
		
		return "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
	}
	
	@Test
	public void Test() throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TUser user = new TUser();
		user.setUsername("吴丛明");
		Class<? extends TUser> clazz = user.getClass();
		Method method = clazz.getMethod("getUsername");
		Object invoke = method.invoke(user);
		
	}
	
}