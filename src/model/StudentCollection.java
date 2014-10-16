/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: StudentCollection is a hashmap of students.
 */

package model;

import java.io.Serializable;
import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student> implements Serializable{
	
	//constructor for our student collection which just calls java's Hashmap.
	
	public StudentCollection(){
		super();
	}
	
	//adds a student to the Hashmap based on their name.
	
	public void addStudent(String name, Student student){
		put(name,student);
	}
	
	//retrieves a student from the Hashmap based on their name
	
	public Student getStudent(String name){
		return get(name);
	}
}