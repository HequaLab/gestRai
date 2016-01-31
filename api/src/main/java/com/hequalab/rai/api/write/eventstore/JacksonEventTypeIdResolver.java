package com.hequalab.rai.api.write.eventstore;

import java.util.Map;

import org.reflections.Reflections;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableMap;
import com.hequalab.rai.dddd.Event;

class JacksonEventTypeIdResolver implements TypeIdResolver {
    
    private static final String EVENT_ROOT_PACKAGE = "com.hequalab.rai";

	private JavaType mBaseType;
    
    private Map<String, Class<? extends Event<?>>> classCache;

	@Override
    public void init(JavaType baseType) {
        mBaseType = baseType;
    }

    @Override
    public Id getMechanism() {
        return Id.CUSTOM;
    }

    @Override
    public String idFromValue(Object obj) {
        return idFromValueAndType(obj, obj.getClass());
    }

    @Override
    public String idFromBaseType() {
        return idFromValueAndType(null, mBaseType.getRawClass());
    }

    @Override
    public String idFromValueAndType(Object obj, Class<?> clazz) {
        return clazz.getSimpleName();
    }

    @Override
    public JavaType typeFromId(String type) {
        if (events().containsKey(type)) {
        	Class<? extends Event<?>> clazz = events().get(type);
	        return TypeFactory.defaultInstance().constructSpecializedType(mBaseType, clazz);
        } else {
            throw new IllegalStateException("Cannot find event '" + type + "'");	        	
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Class<? extends Event<?>>> events() {
    	if (classCache == null) {
    		ImmutableMap.Builder<String, Class<? extends Event<?>>> builder = ImmutableMap.builder();
    		Reflections reflections = new Reflections(EVENT_ROOT_PACKAGE);
	    	for (Class<? extends Event> e :reflections.getSubTypesOf(Event.class)) {
	    		builder.put(e.getSimpleName(), (Class<? extends Event<?>>) e);
	    		// System.out.println(e.getCanonicalName());
	    	}
	    	classCache = builder.build();
    	}
    	return classCache;
    }
    
}