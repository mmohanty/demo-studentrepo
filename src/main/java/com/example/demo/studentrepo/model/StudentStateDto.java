package com.example.demo.studentrepo.model;

import java.io.Serializable;

public class StudentStateDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer rollNumber;
	
	private Integer height;
	
	private Integer weight;
	
	private String stateId;
	
	private String newStateId;
	
	//other attributes


	public Integer getHeight() {
		return height;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getNewStateId() {
		return newStateId;
	}

	public void setNewStateId(String newStateId) {
		this.newStateId = newStateId;
	}
	
}
