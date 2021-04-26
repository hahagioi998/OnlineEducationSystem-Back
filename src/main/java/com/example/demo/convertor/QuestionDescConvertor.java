package com.example.demo.convertor;

import java.util.List;

import javax.persistence.AttributeConverter;


import com.example.demo.entity.QuestionDesc;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionDescConvertor implements AttributeConverter<List<QuestionDesc>, String> {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();

	}

	@Override
	public String convertToDatabaseColumn(List<QuestionDesc> attribute) {
		try {
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public List<QuestionDesc> convertToEntityAttribute(String dbData) {
		try {
			return mapper.readValue(dbData, new TypeReference<List<QuestionDesc>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}
}
