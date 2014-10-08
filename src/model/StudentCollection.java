package model;

import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student>{
	
	private HashMap<String, Student> students;
	
	public StudentCollection(){
		
		students = new HashMap<String, Student>();
	}
}
