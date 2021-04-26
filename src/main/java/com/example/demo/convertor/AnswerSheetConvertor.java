package com.example.demo.convertor;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.example.demo.entity.AnswerSheet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnswerSheetConvertor implements AttributeConverter<List<AnswerSheet>, String> {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();

	}

	@Override
	public String convertToDatabaseColumn(List<AnswerSheet> attribute) {
		try {
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public List<AnswerSheet> convertToEntityAttribute(String dbData) {
		try {
			return mapper.readValue(dbData, new TypeReference<List<AnswerSheet>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}
}
