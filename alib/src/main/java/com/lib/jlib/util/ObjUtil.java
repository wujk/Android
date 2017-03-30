package com.lib.jlib.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjUtil {

	/**
	 * 对象检测
	 * @param clazz
	 * @param obj
	 * @return
	 */
	public static boolean checkClass(Class<?> clazz, Object obj) {
		return clazz.isAssignableFrom(obj.getClass());
	}

	/**
	 * 判断对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (String.class.isAssignableFrom(o.getClass())) {
			String result = (String)o;
			return result.trim().length() == 0;
		} else if (List.class.isAssignableFrom(o.getClass())) {
			List<?> result = (List<?>)o;
			return result.size() == 0;
		} else if (Map.class.isAssignableFrom(o.getClass())) {
			Map<?, ?> result = (Map<?, ?>)o;
			return result.size() == 0;
		} else if (o.getClass().isArray()) {
			return Array.getLength(o) == 0;
		}
		return false;
	}
	
	/**
	 * 获得对象的所有成员变量
	 * @param classTye 对象类型
	 * @return 成员变量list
	 */
	public static List<Field> getDeclaredFields(Class<?> classTye) {
		// 子类成员变量
		Field[] subFiled = classTye.getDeclaredFields();
		// 父类成员变量
		Field[] superField = classTye.getSuperclass().getDeclaredFields();
		// 合并后的成员变量
		List<Object> list = Tools.combineArrays(subFiled, superField);
		List<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < list.size(); i++) {
			fields.add((Field) list.get(i));
		}
		return fields;
	}

	/**
	 * 对象转map
	 * @param object Object类型对象
	 * @return map
	 */
	public static Map<String, Object> toMap(Object object) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (object == null) {
			return map;
		}
		try {
			// 对象类型
			Class<?> classTye = object.getClass();
			// 获得对象的所有成员变量
			List<Field> list = getDeclaredFields(classTye);
			for (int i = 0; i < list.size(); i++) {
				Field field = (Field) list.get(i);
				String fieldName = field.getName();
				field.setAccessible(true);
				Object o = field.get(object);
				if (o != null) {
					map.put(fieldName, o);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
}
