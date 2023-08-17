package com.loiane.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.LessonDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		if (course == null) {
			return null;
		}

		List<LessonDTO> lessonsDTO = course.getLessons()
				.stream()
				.map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
				.collect(Collectors.toList());

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonsDTO);
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
