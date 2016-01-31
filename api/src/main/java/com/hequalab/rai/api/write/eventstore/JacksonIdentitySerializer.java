package com.hequalab.rai.api.write.eventstore;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hequalab.rai.dddd.UUIDIdentity;

public class JacksonIdentitySerializer extends JsonSerializer<UUIDIdentity> {

	@Override
	public void serialize(UUIDIdentity value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {			
		jgen.writeString(value.toString());
	}

}