package com.hequalab.rai.api.write.eventstore;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonTypeIdResolver(JacksonEventTypeIdResolver.class)
public abstract class JacksonEventMixIn {

}