package com.loiane.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loiane.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
