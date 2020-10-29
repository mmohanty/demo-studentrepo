package com.example.demo.studentrepo.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.studentrepo.exception.InvalidArgumentException;

public class StudentBodyMassContext {

	private final NavigableMap<Student, Object> studentBodyMap;
	private final NavigableMap<Student, Object> studentMassMap;
	private static final Object DUMMY_OBJECT = new Object();
	
	//To keep all track of old record. In-case there is change in Student attributes during update, it will help to retrieve old key
	private final Map<Integer, Student> studentMap = new ConcurrentHashMap<>();
	
	public StudentBodyMassContext() {
		super();
		this.studentBodyMap = new TreeMap<>(new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				if(null == s1 || null == s2 || null == s1.getHeight() || null == s2.getHeight() || null == s1.getRollNumber() || null == s2.getRollNumber() ) {
					throw new InvalidArgumentException("Invalid state of object");
				}
				int heightDiff = s1.getHeight().compareTo(s2.getHeight());
				if(heightDiff != 0) {
					return heightDiff;
				}
				return s1.getRollNumber().compareTo(s2.getRollNumber());
			}
			
		});
		this.studentMassMap = new TreeMap<>(new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				if(null == s1 || null == s2 || null == s1.getWeight() || null == s2.getWeight() || null == s1.getRollNumber() || null == s2.getRollNumber() ) {
					throw new InvalidArgumentException("Invalid state of object");
				}
				int weightDiff = s1.getWeight().compareTo(s2.getWeight());
				if(weightDiff != 0) {
					return weightDiff;
				}
				return s1.getRollNumber().compareTo(s2.getRollNumber());
			}
			
		});
	}
	
	
	public void addStudent(final Student student) {
		if(studentMap.containsKey(student.getRollNumber())) {
			return;
		}
		
		this.studentBodyMap.put(student, DUMMY_OBJECT);
		this.studentMassMap.put(student, DUMMY_OBJECT);
		studentMap.put(student.getRollNumber(), student);
	}
	
	public void deleteStudent(final Integer studentRollNumber) {
		if(!studentMap.containsKey(studentRollNumber)) {
			return;
		}
		Student studentKey = studentMap.get(studentRollNumber);
		this.studentBodyMap.remove(studentKey);
		this.studentMassMap.remove(studentKey);
		studentMap.remove(studentRollNumber);
	}
	
	public List<Student> getTallestStudent() {
		List<Student> students = new ArrayList<>();
		Student student = this.studentBodyMap.lastKey();
		do {
			if(student != null) {
				students.add(student);
			}
			Student secondStudent = this.studentBodyMap.lowerKey(student);
			if(secondStudent != null && secondStudent.getHeight() == student.getHeight()) {
				student = secondStudent;
			}else {
				break;
			}
		}while(true);
		return students;
	}
	
	public List<Student> getHeaviestStudent() {
		List<Student> students = new ArrayList<>();
		Student student = this.studentMassMap.lastKey();
		do {
			if(student != null) {
				students.add(student);
			}
			Student secondStudent = this.studentMassMap.lowerKey(student);
			if(secondStudent != null && secondStudent.getWeight() == student.getWeight()) {
				student = secondStudent;
			}else {
				break;
			}
		}while(true);
		return students;
	}
	
	public int size() {
		return studentMap.size();
	}
}
