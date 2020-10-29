package com.example.demo.studentrepo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.studentrepo.model.Student;
import com.example.demo.studentrepo.model.StudentStateDto;
import com.example.demo.studentrepo.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	@Qualifier("InMemoryStudentServiceImpl")
	private StudentService studentService;
	
	@PostMapping("/student")
	public void addStudent(@RequestBody StudentStateDto studentDto) {
		Student student = new Student(studentDto.getRollNumber(), studentDto.getHeight(), studentDto.getWeight());
		studentService.addStudent(student, studentDto.getStateId());
	}
	
	@DeleteMapping("/student")
	public void deleteStudent(@RequestBody StudentStateDto studentDto) {
		studentService.deleteStudent(studentDto.getRollNumber(), studentDto.getStateId());
	}
	
	@PutMapping("/student")
	public void updateStudent(@RequestBody StudentStateDto studentDto) {
		Student student = new Student(studentDto.getRollNumber(), studentDto.getHeight(), studentDto.getWeight());
		studentService.updateStudent(student, studentDto.getNewStateId(), studentDto.getStateId());
	}
	
	
	@GetMapping("/student/tallest/{state}")
	public List<Student> findTallestStudent(@PathVariable String stateId) {
		return studentService.findTallestStudent(stateId);
	}
	
	@GetMapping("/student/heaviest/{state}")
	public List<Student> findHeaviestStudent(@PathVariable String stateId) {
		return studentService.findHeaviestStudent(stateId);
	}
	
	
}
