package com.example.demo.studentrepo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.studentrepo.model.Student;

public interface StudentService {
	
	public void addStudent(Student student, String stateId);
	
	public void deleteStudent(Integer rollNumber, String stateId);
	
	public void updateStudent(Student student, String newStateId, String oldStateId) ;
	
	public  List<Student> findTallestStudent(@PathVariable String stateId) ;
	
	public  List<Student> findHeaviestStudent(@PathVariable String stateId) ;
}
