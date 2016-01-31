package com.hequalab.rai.dddd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import com.google.common.collect.Lists;

public class Reflection {

	public static Collection<Method> annotatedMethods(Object target, Class<? extends Annotation> annotation) {
		Collection<Method> methods = Lists.newArrayList();
		for (Method method : target.getClass().getMethods()) {						
			if (method.getAnnotation(annotation) != null) {
				methods.add(method);
			}
		}
		return methods;
	}

	public static Collection<Field> annotatedFields(Class<?> target, Class<? extends Annotation> annotation) {
		Collection<Field> fields = Lists.newArrayList();
		for (Field field : target.getFields()) {						
			if (field.getAnnotation(annotation) != null) {
				fields.add(field);
			}
		}
		return fields;
	}

}