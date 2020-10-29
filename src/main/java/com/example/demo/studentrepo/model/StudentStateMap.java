package com.example.demo.studentrepo.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentStateMap {

	private static final StudentStateMap STUDENT_STATE_MAP = new StudentStateMap();
	
	private final Map<String, StudentBodyMassContext> map;
	
	private StudentStateMap() {
		map = new ConcurrentHashMap<>();
	}
	
	public static StudentStateMap getInstance() {
		return STUDENT_STATE_MAP;
	}
	
	public StudentBodyMassContext getStudentBodyMassContext(String state) {
		return map.get(state);
	}
	
	public void addNewStudentBodyMassContext(String state) {
		map.putIfAbsent(state, new StudentBodyMassContext());
	}
	
}
