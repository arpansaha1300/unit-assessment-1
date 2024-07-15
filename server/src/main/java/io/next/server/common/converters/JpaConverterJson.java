package io.next.server.common.converters;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<ArrayList<?>, String> {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(ArrayList<?> meta) {
    try {
      return objectMapper.writeValueAsString(meta);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }

  @Override
  public ArrayList<?> convertToEntityAttribute(String dbData) {
    ArrayList<?> list = new ArrayList<>();

    try {
      list = objectMapper.readValue(dbData, new TypeReference<ArrayList<?>>() {
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

}