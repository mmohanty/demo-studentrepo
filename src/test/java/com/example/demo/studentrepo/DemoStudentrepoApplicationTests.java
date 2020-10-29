package com.example.demo.studentrepo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.studentrepo.controller.StudentController;
import com.example.demo.studentrepo.model.Student;
import com.example.demo.studentrepo.model.StudentStateDto;

@SpringBootTest
class DemoStudentrepoApplicationTests {

	private Student student1;
	private Student student1Duplicate;
	private Student student2;
	private Student student3;
	private Student student4;
	
	private StudentStateDto studentHydDto1;
	private StudentStateDto studentHydDto1Duplicate;
	private StudentStateDto studentHydDto2;
	private StudentStateDto studentHydDto3;
	private StudentStateDto studentHydDto4;
	
	private StudentStateDto studentBlrDto1;
	private StudentStateDto studentBlrDto1Duplicate;
	private StudentStateDto studentBlrDto2;
	private StudentStateDto studentBlrDto3;
	private StudentStateDto studentBlrDto4;

	@Autowired
	private StudentController studentController;
	private String []stateIds = new String[2];

	
	@BeforeEach
	public void setup() {
		stateIds[0] = "HYD";
		stateIds[1] = "BLR";
		// Student(rollNumber, heightInCms, weight) for Hyderabad
		student1 = new Student(1, 100, 50); // second tallest and second heaviest
		student1Duplicate = new Student(1, 100, 50); // same as student 1 - duplicate entry
		student2 = new Student(2, 100, 50); // second tallest and second heaviest
		student3 = new Student(3, 130, 40); // Most Tallest and least heaviest
		student4 = new Student(4, 90, 60); // least Tallest and most heaviest
		
		//build for HYD
		studentHydDto1 = buildDTO(student1, stateIds[0]);
		studentHydDto2 = buildDTO(student2,stateIds[0]);
		studentHydDto3 = buildDTO(student3, stateIds[0]);
		studentHydDto4 = buildDTO(student4, stateIds[0]);
		studentHydDto1Duplicate = buildDTO(student1Duplicate, stateIds[0]);
		
		//build for BLR
		studentBlrDto1 = buildDTO(student1, stateIds[1]);
		studentBlrDto2 = buildDTO(student2,stateIds[1]);
		studentBlrDto3 = buildDTO(student3, stateIds[1]);
		studentBlrDto4 = buildDTO(student4, stateIds[1]);
		studentBlrDto1Duplicate = buildDTO(student1Duplicate, stateIds[1]);
	}

	private StudentStateDto buildDTO(Student student, String stateId) {
		StudentStateDto studentStateDto = new StudentStateDto();
		studentStateDto.setHeight(student.getHeight());
		studentStateDto.setWeight(student.getWeight());
		studentStateDto.setRollNumber(student.getRollNumber());
		studentStateDto.setStateId(stateId);
		return studentStateDto;
	}

	private void addStudents() {
		studentController.addStudent(studentHydDto1);
		studentController.addStudent(studentHydDto2);
		studentController.addStudent(studentHydDto3);
		studentController.addStudent(studentHydDto4);
		studentController.addStudent(studentHydDto1Duplicate);
		
		studentController.addStudent(studentBlrDto1);
		studentController.addStudent(studentBlrDto2);
		studentController.addStudent(studentBlrDto3);
		studentController.addStudent(studentBlrDto4);
		studentController.addStudent(studentBlrDto1Duplicate);
	}
	
	private void verifyTallest(List<Student> tallestStudentsOfHyderabad) {
		Assertions.assertNotNull(tallestStudentsOfHyderabad);
		Assertions.assertEquals(1, tallestStudentsOfHyderabad.size());
		Assertions.assertEquals(130, tallestStudentsOfHyderabad.get(0).getHeight());
		Assertions.assertEquals(3, tallestStudentsOfHyderabad.get(0).getRollNumber());
		Assertions.assertEquals(40, tallestStudentsOfHyderabad.get(0).getWeight());
	}
	private void verifyHeaviest(List<Student> heavStudents) {
		Assertions.assertNotNull(heavStudents);
		Assertions.assertEquals(1, heavStudents.size());
		Assertions.assertEquals(90, heavStudents.get(0).getHeight());
		Assertions.assertEquals(4, heavStudents.get(0).getRollNumber());
		Assertions.assertEquals(60, heavStudents.get(0).getWeight());
	}
	
	@Test
	void test_add_and_verify_tallestStudentOfHYD() {
		addStudents();
		
		List<Student> tallestStudentsOfHyderabad = studentController.findTallestStudent(stateIds[0]);
		verifyTallest(tallestStudentsOfHyderabad);
	}

	@Test
	void test_add_and_verify_tallestStudentOfBLR() {
		addStudents();
		
		List<Student> tallestStudentsOfHyderabad = studentController.findTallestStudent(stateIds[1]);
		verifyTallest(tallestStudentsOfHyderabad);
	}

	
	@Test
	void test_add_and_verify_HeaviestStudentOfHYD() {
		addStudents();
		
		List<Student> heavStudents = studentController.findHeaviestStudent(stateIds[0]);
		verifyHeaviest(heavStudents);
	}

	@Test
	void test_add_and_verify_HeaviestStudentOfBLR() {
		addStudents();
		
		List<Student> heavStudents = studentController.findHeaviestStudent(stateIds[1]);
		verifyHeaviest(heavStudents);
	}
	
	//@Test
	void test_Update_and_delete() {
		addStudents();
		
		//add a new student to HYD
		StudentStateDto studentDto = buildDTO(new Student(5, 160, 80), stateIds[0]);
		studentController.addStudent(studentDto);
		
		//verify  student of HYD
		List<Student> heavStudentsOfHyd = studentController.findHeaviestStudent(stateIds[0]);
		verifyUpdate(heavStudentsOfHyd);
		
		List<Student> tallestStudentsOfHyd = studentController.findTallestStudent(stateIds[0]);
		verifyUpdate(tallestStudentsOfHyd);
		
		//verify  student of BLR
		List<Student> heavStudentsOfBlr = studentController.findHeaviestStudent(stateIds[1]);
		verifyHeaviest(heavStudentsOfBlr);
		
		List<Student> tallestStudentsOfBlr = studentController.findTallestStudent(stateIds[1]);
		verifyTallest(tallestStudentsOfBlr);
		
		//Transfer student5 to BLR
		studentDto.setNewStateId(stateIds[1]);
		studentController.updateStudent(studentDto);
		
		//verify tallest student of BLR
		heavStudentsOfBlr = studentController.findHeaviestStudent(stateIds[1]);
		verifyUpdate(heavStudentsOfBlr);
		
		tallestStudentsOfBlr = studentController.findTallestStudent(stateIds[1]);
		verifyUpdate(tallestStudentsOfBlr);
		
		//verify tallest student of HYD
		heavStudentsOfHyd = studentController.findHeaviestStudent(stateIds[0]);
		verifyHeaviest(heavStudentsOfHyd);
		
		tallestStudentsOfHyd = studentController.findTallestStudent(stateIds[0]);
		verifyTallest(tallestStudentsOfHyd);
		
		//remove student5 from list - as this is integration test and will break other Asserttions
		studentController.deleteStudent(studentDto);
		
		//verify tallest student of BLR
		heavStudentsOfBlr = studentController.findHeaviestStudent(stateIds[1]);
		verifyUpdate(heavStudentsOfBlr);
		
		tallestStudentsOfBlr = studentController.findTallestStudent(stateIds[1]);
		verifyUpdate(tallestStudentsOfBlr);
		
		//verify tallest student of HYD
		heavStudentsOfHyd = studentController.findHeaviestStudent(stateIds[0]);
		verifyHeaviest(heavStudentsOfHyd);
		
		tallestStudentsOfHyd = studentController.findTallestStudent(stateIds[0]);
		verifyTallest(tallestStudentsOfHyd);
	}

	private void verifyUpdate(List<Student> tallestStudents) {
		Assertions.assertNotNull(tallestStudents);
		Assertions.assertEquals(1, tallestStudents.size());
		Assertions.assertEquals(160, tallestStudents.get(0).getHeight());
		Assertions.assertEquals(5, tallestStudents.get(0).getRollNumber());
		Assertions.assertEquals(80, tallestStudents.get(0).getWeight());
	}
}
