package com.loiane.dto.mapper;

import org.springframework.stereotype.Component;

import com.loiane.model.Course;
import com.loiane.dto.CourseDTO;
import com.loiane.enums.Category;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		if (course == null) {
			return null;
		}
		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
	}

	public Course toEntity(CourseDTO courseDTO) {
		if (courseDTO == null) {
			return null;
		}
		Course course = new Course();
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}
		if (courseDTO.name() != null) {
			course.setName(courseDTO.name());
		}
		if (courseDTO.category() != null) {
			course.setCategory(convertCategoryValue(courseDTO.category()));
		}
		return course;
	}

	public Category convertCategoryValue(String value) {
		if (value == null) {
			return null;
		}
		return switch (value) {
			case "Front-end" -> Category.FROTEND;
			case "Back-end" -> Category.BACKEND;
			default -> throw new IllegalArgumentException("Invalid value");
		};
	}

}
