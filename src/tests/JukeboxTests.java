/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: This class is JukeboxTests and are the J unit tests for the model/GUI
 */

package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.CardReader;
import model.PlayList;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;

import org.junit.Test;

public class JukeboxTests {
	
	Student Ali = new Student("Ali","1111");
	Student Chris = new Student("Chris", "2222");
	Student River = new Student("River", "3333");
	Student Ryan = new Student("Ryan", "4444");
	
	Song blue = new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3");
	Song tumbao = new Song("Determined Tumbao", 20, "FreePlay Music", "./songfiles/DeterminedTumbao.mp3" );
	Song flute = new Song("Flute", 5, "Sun Microsystems", "./songfiles/flute.aif" );
	Song space = new Song("Space Music", 6, "Unknown", "./songfiles/spacemusic.au");
	Song swing = new Song("Swing Cheese", 15, "FreePlay Music", "./songfiles/SwingCheese.mp3" );
	Song tada = new Song("Tada", 2, "Microsoft", "./songfiles/tada.wav" );
	Song fire = new Song("Untameable Fire", 282, "Pierre Langer", "./songfiles/UntameableFire.mp3" );
	
	SongCollection songs = new SongCollection();
	StudentCollection students = new StudentCollection();
	PlayList playlist = new PlayList();
	
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
		
		assertFalse(cardReader.validate("Jason", "1111", students));
		
		cardReader.validate("Chris", "3333", students);
		assertFalse(students.getStudent("Chris").getAuthenticatedStatus());
		
		assertFalse(students.getStudent("River").getAuthenticatedStatus());
		cardReader.validate("River", "3333", students);
		assertTrue(students.getStudent("River").getAuthenticatedStatus());
		
		assertFalse(students.getStudent("Ryan").getAuthenticatedStatus());
		cardReader.validate("Ryan", "4444", students);
		assertTrue(students.getStudent("Ryan").getAuthenticatedStatus());
	}
	
	@Test
	public void testSongCollection(){
		
		SongCollection songs = new SongCollection();
		Song song1 = new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3" );
		songs.add(song1);
		
		assertEquals(songs.getColumnName(0), "Artist");
		assertEquals(songs.getColumnName(1), "Title");
		assertEquals(songs.getColumnName(2), "Seconds");
		
		assertEquals(songs.getColumnCount(), 3);
		
		assertEquals(songs.getRowCount(), 1);
		
		assertEquals(songs.getValueAt(0, 0), "Ralph Schuckett");
		assertEquals(songs.getValueAt(0, 1), "Blue Ridge Mountain Mist");
		assertEquals(songs.getValueAt(0, 2), 38);
		
		assertEquals(songs.getColumnClass(0), String.class);
		assertEquals(songs.getColumnClass(1), String.class);
		assertEquals(songs.getColumnClass(2), Integer.class);
		
		songs.remove(song1);
		
		assertEquals(songs.getRowCount(), 0);
		
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
		
		assertEquals(song1.getFileName(), "./songfiles/BlueRidgeMountainMist.mp3");
	}
	
	@Test
	public void testRefreshCount(){

		GregorianCalendar day1 = new GregorianCalendar();
		GregorianCalendar day2 = new GregorianCalendar();
		day2.set(day1.get(Calendar.YEAR), day1.get(Calendar.MONTH), day1.get(Calendar.DAY_OF_MONTH)+1);
		
		playlist.queueUpNextSong(blue);
		
		songs.add(blue);
		songs.add(tumbao);
		songs.add(flute);
		songs.add(space);
		songs.add(swing);
		songs.add(tada);
		songs.add(fire);

		students.addStudent("Ali", Ali);
		students.addStudent("Chris", Chris);
		students.addStudent("River", River);
		students.addStudent("Ryan", Ryan);
		
		assertEquals(Chris.getSongsPlayedToday(),0);
		Chris.playSong(blue, day1, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),1);
		Chris.playSong(blue, day1, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),2);
		Chris.playSong(blue, day1, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),2);
		assertEquals(blue.getPlaysToday(),2);
		River.playSong(blue, day1, songs, students);
		assertEquals(blue.getPlaysToday(),3);
		assertEquals(River.getSongsPlayedToday(),1);
		River.playSong(blue, day1, songs, students);
		assertEquals(blue.getPlaysToday(),4);
		assertEquals(River.getSongsPlayedToday(),2);
		River.playSong(blue, day1, songs, students);
		assertEquals(blue.getPlaysToday(),4);
		assertEquals(River.getSongsPlayedToday(),2);
		Ryan.playSong(blue, day1, songs, students);
		assertEquals(blue.getPlaysToday(),5);
		assertEquals(Ryan.getSongsPlayedToday(),1);
		Ryan.playSong(blue, day1, songs, students);
		assertEquals(blue.getPlaysToday(),5);
		assertEquals(Ryan.getSongsPlayedToday(),1);
		
		Chris.playSong(blue, day2, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),1);
		Chris.playSong(blue, day2, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),2);
		Chris.playSong(blue, day2, songs, students);
		assertEquals(Chris.getSongsPlayedToday(),2);
		assertEquals(blue.getPlaysToday(),2);
		River.playSong(blue, day2, songs, students);
		assertEquals(blue.getPlaysToday(),3);
		assertEquals(River.getSongsPlayedToday(),1);
		River.playSong(blue, day2, songs, students);
		assertEquals(blue.getPlaysToday(),4);
		assertEquals(River.getSongsPlayedToday(),2);
		River.playSong(blue, day2, songs, students);
		assertEquals(blue.getPlaysToday(),4);
		assertEquals(River.getSongsPlayedToday(),2);
		Ryan.playSong(blue, day2, songs, students);
		assertEquals(blue.getPlaysToday(),5);
		assertEquals(Ryan.getSongsPlayedToday(),1);
		Ryan.playSong(blue, day2, songs, students);
		assertEquals(blue.getPlaysToday(),5);
		assertEquals(Ryan.getSongsPlayedToday(),1);
	}
}