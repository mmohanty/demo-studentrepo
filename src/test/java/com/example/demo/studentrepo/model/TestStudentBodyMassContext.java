package com.example.demo.studentrepo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStudentBodyMassContext {

	private StudentBodyMassContext bodyMassContext;
	private Student student1 ;
	private Student student1Duplicate;
	private Student student2;
	private Student student3;
	private Student student4;
	
	@BeforeEach
	void setUp() throws Exception {
		bodyMassContext = new StudentBodyMassContext();
		//Student(rollNumber, heightInCms, weight)
		student1= new Student(1, 100, 50); // second tallest and second heaviest
		student1Duplicate = new Student(1, 100, 50); // same as student 1 - duplicate entry
		student2 = new Student(2, 100, 50); //second tallest and second heaviest
		student3 = new Student(3, 130, 40); //Most Tallest and least heaviest
		student4 = new Student(4, 90, 60); //least Tallest and most heaviest
	}

	private void addStudentsToContext() {
		bodyMassContext.addStudent(student1);
		bodyMassContext.addStudent(student1Duplicate);
		bodyMassContext.addStudent(student2);
		bodyMassContext.addStudent(student4);
		bodyMassContext.addStudent(student3);
	}
	
	@Test
	void test_Add_when_addingThreeUniqueAndOneDuplicateStudent_expected_sizeEqualsThree() {
		addStudentsToContext();
		Assertions.assertEquals(4, bodyMassContext.size());
		Assertions.assertEquals(1, bodyMassContext.getHeaviestStudent().size());
		Assertions.assertEquals(1, bodyMassContext.getTallestStudent().size());
		Assertions.assertEquals(130, bodyMassContext.getTallestStudent().get(0).getHeight());
		Assertions.assertEquals(60, bodyMassContext.getHeaviestStudent().get(0).getWeight());
	}

	@Test
	void test_Delete_when_deleting_secondHeaviestStudent_expected_HeaviestStudentReminAsIs() {
		addStudentsToContext();
		bodyMassContext.deleteStudent(1);
		Assertions.assertEquals(3, bodyMassContext.size());
		Assertions.assertEquals(1, bodyMassContext.getHeaviestStudent().size());
		Assertions.assertEquals(60, bodyMassContext.getHeaviestStudent().get(0).getWeight());
	}
	
	@Test
	void test_Delete_when_deleting_heaviestStudent_expected_secondHeaviestIs_HeaviestNow() {
		addStudentsToContext();
		bodyMassContext.deleteStudent(4);
		Assertions.assertEquals(3, bodyMassContext.size());
		Assertions.assertEquals(2, bodyMassContext.getHeaviestStudent().size());
		Assertions.assertEquals(50, bodyMassContext.getHeaviestStudent().get(0).getWeight());
		Assertions.assertEquals(50, bodyMassContext.getHeaviestStudent().get(1).getWeight());
	}
	
	@Test
	void test_Delete_when_deleting_secondTallestStudent_expected_TallestStudentReminAsIs() {
		addStudentsToContext();
		bodyMassContext.deleteStudent(1);
		Assertions.assertEquals(3, bodyMassContext.size());
		Assertions.assertEquals(1, bodyMassContext.getTallestStudent().size());
		Assertions.assertEquals(130, bodyMassContext.getTallestStudent().get(0).getHeight());
	}
	
	@Test
	void test_Delete_when_deleting_TallestStudent_expected_secondTallestIs_TallestNow() {
		addStudentsToContext();
		bodyMassContext.deleteStudent(3);
		Assertions.assertEquals(3, bodyMassContext.size());
		Assertions.assertEquals(2, bodyMassContext.getTallestStudent().size());
		Assertions.assertEquals(100, bodyMassContext.getTallestStudent().get(0).getHeight());
		Assertions.assertEquals(100, bodyMassContext.getTallestStudent().get(1).getHeight());
	}
}
