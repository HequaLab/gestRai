package com.hequalab.rai.api.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.reflections.Reflections;

import com.google.common.collect.ImmutableList;

public class ReflectionUtils {
	

    public static <T> ImmutableList<Class<?>> findClassesByBaseType(String pckg, Class<T> type) {
    	ImmutableList.Builder<Class<?>> builder = ImmutableList.builder();
		Reflections reflections = new Reflections(pckg);
		Set<Class<? extends T>> items = reflections.getSubTypesOf(type);
    	for (Class<? extends T> e : items) {
    		if (!Modifier.isAbstract(e.getModifiers())) {
    			builder.add((Class<?>)  e);
    		}
    	}
    	return builder.build();    	
	}
    
    public static <T extends Annotation> ImmutableList<Class<?>> findClassesAnnotatatedBy(String pckg, Class<T> type) {
    	ImmutableList.Builder<Class<?>> builder = ImmutableList.builder();
		Reflections reflections = new Reflections(pckg);
		Set<Class<?>> items = reflections.getTypesAnnotatedWith(type);
    	for (Class<?> e : items) {
    		builder.add((Class<?>)  e);
    	}
    	return builder.build();    	
	}

}