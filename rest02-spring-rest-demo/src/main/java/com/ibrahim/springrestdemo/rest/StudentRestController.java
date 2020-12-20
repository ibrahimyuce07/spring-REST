package com.ibrahim.springrestdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahim.springrestdemo.entity.Student;

/* retrive POJOs as JSON example */

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;

	// define @PostConstruct to load the student data ... only once!

	@PostConstruct
	public void loadData() {
		// hard coded for now, db later.
		theStudents = new ArrayList<>();

		theStudents.add(new Student("ibrahim", "yüce"));
		theStudents.add(new Student("metin", "yüce"));
		theStudents.add(new Student("efe", "yüce"));
	}

	// define endpoint for "/students" -return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {

		return theStudents;
	}

	// define endpoint for "/students/{studentId}" - return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		// just index into the list ... keep it simple for now..

		// check the student id against list size
		if (studentId >= theStudents.size() || studentId < 0) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}

		return theStudents.get(studentId);
	}

}
