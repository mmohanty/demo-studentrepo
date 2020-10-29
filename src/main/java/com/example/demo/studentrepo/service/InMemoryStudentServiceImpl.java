package com.example.demo.studentrepo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.studentrepo.model.Student;
import com.example.demo.studentrepo.model.StudentBodyMassContext;
import com.example.demo.studentrepo.model.StudentStateMap;

@Component("InMemoryStudentServiceImpl")
public class InMemoryStudentServiceImpl implements StudentService {

	private final StudentStateMap studentStateMap;
	
	
	public InMemoryStudentServiceImpl() {
		studentStateMap = StudentStateMap.getInstance();
	}
	
	@Override
	public void addStudent(Student student, String stateId) {
		if(stateId == null) {
			//log something
			return;
		}
		studentStateMap.addNewStudentBodyMassContext(stateId);
		StudentBodyMassContext bodyMassContext = studentStateMap.getStudentBodyMassContext(stateId);
		bodyMassContext.addStudent(student);

	}

	@Override
	public void deleteStudent(Integer rollNumber, String stateId) {
		if(stateId == null || rollNumber == null) {
			//log something
			return;
		}
		StudentBodyMassContext bodyMassContext = studentStateMap.getStudentBodyMassContext(stateId);
		if(bodyMassContext == null) {
			return;
		}
		//get old key
		bodyMassContext.deleteStudent(rollNumber);
	}

	@Override
	public void updateStudent(Student student, String newStateId, String oldStateId) {
		if(oldStateId == null) {
			//log something
			return;
		}
		if(newStateId != null && !newStateId.equals(oldStateId)) {
			// changing state
			deleteStudent(student.getRollNumber(), oldStateId);
			addStudent(student, newStateId);
		}else {
			// changing other attributes of Student
			deleteStudent(student.getRollNumber(), oldStateId);
			addStudent(student, newStateId);
		}

	}

	@Override
	public List<Student> findTallestStudent(String stateId) {
		List<Student> list = null;
		if(stateId == null) {
			//log something
			list =  Collections.emptyList();
		}
		StudentBodyMassContext bodyMassContext = studentStateMap.getStudentBodyMassContext(stateId);
		if(bodyMassContext == null) {
			list = Collections.emptyList();
		}
		list = bodyMassContext.getTallestStudent();
		return list;

	}

	@Override
	public List<Student> findHeaviestStudent(String stateId) {
		List<Student> list = null;
		if(stateId == null) {
			//log something
			list =  Collections.emptyList();
		}
		StudentBodyMassContext bodyMassContext = studentStateMap.getStudentBodyMassContext(stateId);
		if(bodyMassContext == null) {
			list = Collections.emptyList();
		}
		list = bodyMassContext.getHeaviestStudent();
		return list;
	}

}
