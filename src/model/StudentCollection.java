/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: StudentCollection is a hashmap of students.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student> implements Serializable{
	
	public StudentCollection(){
		super();
	}
	
	public void addStudent(String name, Student student){
		put(name,student);
	}
	
	public Student getStudent(String name){
		return get(name);
	}
}
