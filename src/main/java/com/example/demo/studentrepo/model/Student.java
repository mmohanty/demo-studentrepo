package com.example.demo.studentrepo.model;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Integer rollNumber;
	
	private final Integer height;
	
	private final Integer weight;
	
	//other attributes

	/**
	 * Constructs Student Object
	 * @param rollNumber Integer value
	 * @param height Height of Student in centimeters
	 * @param weight Weight of student in Kilograms, Rounded value
	 */
	public Student(Integer rollNumber, Integer height, Integer weight) {
		super();
		this.rollNumber = rollNumber;
		this.height = height;
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}
	
	public Integer getWeight() {
		return weight;
	}


	public Integer getRollNumber() {
		return rollNumber;
	}

	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", height=" + height + ", weight=" + weight + "]";
	}

}
