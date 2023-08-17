package com.loiane;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loiane.enums.Category;
import com.loiane.model.Course;
import com.loiane.model.Lesson;
import com.loiane.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular");
			c.setCategory(Category.FROTEND);
			
			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("Nb4uxLxdvxo");
			l.setCourse(c);
			c.getLessons().add(l);
			
			Lesson l2 = new Lesson();
			l2.setName("Introdução 2");
			l2.setYoutubeUrl("Nb4uxLxdvxE");
			l2.setCourse(c);
			c.getLessons().add(l2);
			
			courseRepository.save(c);
		};
	}

}
