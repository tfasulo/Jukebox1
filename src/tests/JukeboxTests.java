package tests;

import static org.junit.Assert.*;
import model.CardReader;
import model.Song;
import model.Student;
import model.StudentCollection;

import org.junit.Test;

public class JukeboxTests {
	
	@Test
	public void testStudentAuthentication(){
		
		StudentCollection students = new StudentCollection();
		students.addStudent("Ali", new Student("Ali", "1111"));
		assertEquals(students.getStudent("Ali").getId(), "Ali"); 
		CardReader cardReader = new CardReader();
		assertFalse(students.getStudent("Ali").getAuthenticatedStatus());
		cardReader.validate("Ali", "1111", students);
		assertTrue(students.getStudent("Ali").getAuthenticatedStatus());
		
		students.addStudent("Chris", new Student("Chris", "2222"));
		students.addStudent("River", new Student("River", "3333"));
		students.addStudent("Ryan", new Student("Ryan", "4444"));
		
		assertFalse(students.getStudent("Chris").getAuthenticatedStatus());
		cardReader.validate("Chris", "2222", students);
		assertTrue(students.getStudent("Chris").getAuthenticatedStatus());
		
		assertFalse(students.getStudent("River").getAuthenticatedStatus());
		cardReader.validate("River", "3333", students);
		assertTrue(students.getStudent("River").getAuthenticatedStatus());
		
		assertFalse(students.getStudent("Ryan").getAuthenticatedStatus());
		cardReader.validate("Ryan", "4444", students);
		assertTrue(students.getStudent("Ryan").getAuthenticatedStatus());
	}
	
	@Test
	public void testSongPlayedFiveTimes(){
		
		Song song1 = new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3" );
		assertEquals(song1.getPlaysToday(), 0);
		song1.played();
		assertEquals(song1.getPlaysToday(), 1);
		song1.played();
		assertEquals(song1.getPlaysToday(), 2);
		song1.played();
		assertEquals(song1.getPlaysToday(), 3);
		song1.played();
		assertEquals(song1.getPlaysToday(), 4);
		song1.played();
		assertEquals(song1.getPlaysToday(), 5);
	}

}
