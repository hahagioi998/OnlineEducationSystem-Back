


package com.example.demo.convertor;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.example.demo.entity.MarkAttendance;
import com.example.demo.entity.QuestionDesc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AttendanceConvertor implements AttributeConverter<List<MarkAttendance>, String> {
	
		private static ObjectMapper mapper;

		static {
			mapper = new ObjectMapper();

		}

		@Override
		public String convertToDatabaseColumn(List<MarkAttendance> attribute) {
			try {
				return mapper.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				return null;
			}
		}

		@Override
		public List<MarkAttendance> convertToEntityAttribute(String dbData) {
			try {
				return mapper.readValue(dbData, new TypeReference<List<MarkAttendance>>() {
				});
			} catch (Exception e) {
				return null;
			}
		}
	}