package com.loiane.enums.converters;

import java.util.stream.Stream;

import com.loiane.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status status) {
		if (status == null) {
			return null;
		}
		return status.getValue();
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return Stream.of(Status.values()).filter(c -> c.getValue().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}