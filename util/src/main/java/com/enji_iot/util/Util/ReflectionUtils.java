package com.enji_iot.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            if(clazz == null) return null;
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return getMethod(clazz.getSuperclass(), methodName, parameterTypes);
        }
    }
	
	public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
        Field field = null;
        try {
            if(clazz == null) return null;
            field = clazz.getDeclaredField(fieldName);
            return field;
        } catch (NoSuchFieldException e) {
            return getField(clazz.getSuperclass(), fieldName);
        }
    }
}
