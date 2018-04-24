package com.is.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
				fromField = fromClazz.getDeclaredField(field.getName());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			try {
				String methodName = getSetMethodName(field.getName());
				Method method = desClazz.getMethod(methodName, field.getType());
				fromField.setAccessible(true);
				method.invoke(desObject, fromField.get(fromObject));
			}catch(NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	private static String getSetMethodName(String filedName) throws Exception {
		if(filedName == null || filedName.trim().equals(""))
			throw new Exception("filedName为空或没有");
		
		return "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
	}
	
}