/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: StudentCollection is a hashmap of students.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student> implements Serializable{
	
	//private HashMap<String, Student> students;
	
	public StudentCollection(){
		super();
		//students = new HashMap<String, Student>();
	}
	
	public void addStudent(String name, Student student){
		put(name,student);
		//students.put(name, student);
	}
	
	public Student getStudent(String name){
		return get(name);
		//return students.get(name);
	}
}
