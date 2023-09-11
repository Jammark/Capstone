package com.capstone.progettofinale.common;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Jackson serializer for LocalTime with format "HH:mm:ss"
 */
@JsonComponent
public class LocalTimeJsonSerializer extends JsonDeserializer<LocalTime> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	/*
	 * @Override public void serialize(LocalTime value, JsonGenerator gen,
	 * SerializerProvider serializers) throws IOException {
	 * 
	 * if (value == null) { gen.writeNull(); } else {
	 * gen.writeString(formatter.format(value)); }
	 * 
	 * }
	 */
	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		return LocalTime.parse(p.getText(), formatter);
	}

}