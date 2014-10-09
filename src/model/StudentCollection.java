package model;

import java.util.HashMap;

public class StudentCollection{ //extends HashMap<String, Student>{
	
	private HashMap<String, Student> students;
	
	public StudentCollection(){
		
		students = new HashMap<String, Student>();
	}
	
	public void addStudent(String name, Student student){
		students.put(name, student);
	}
	
	public Student getStudent(String name){
		
		return students.get(name);
	}
}
