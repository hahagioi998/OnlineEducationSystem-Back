package com.example.demo.convertor;


import java.util.List;

import javax.persistence.AttributeConverter;

import com.example.demo.entity.ScheduleLecture;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ScheduleLectureConvertor implements AttributeConverter<List<ScheduleLecture>, String> {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();

	}

	@Override
	public String convertToDatabaseColumn(List<ScheduleLecture> attribute) {
		try {
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public List<ScheduleLecture> convertToEntityAttribute(String dbData) {
		try {
			return mapper.readValue(dbData, new TypeReference<List<ScheduleLecture>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}
}