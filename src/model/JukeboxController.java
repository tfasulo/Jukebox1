package model;

import model.CardReader;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;


public class JukeboxController{


	private SongCollection songs;

	private StudentCollection students = new StudentCollection();
	private Student currentStudent = new Student(null, null);
	private CardReader reader = new CardReader();
	boolean validated = false;
	private PlayList playlist;

	
	public JukeboxController(){
		
		students.addStudent("Ali", new Student("Ali", "1111"));
		students.addStudent("Chris", new Student("Chris", "2222"));
		students.addStudent("River", new Student("River", "3333"));
		students.addStudent("Ryan", new Student("Ryan", "4444"));
		
		songs = new SongCollection();
		songs.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3" ));
		songs.add(new Song("Determined Tumbao", 20, "FreePlay Music", "./songfiles/DeterminedTumbao.mp3" ));
		songs.add(new Song("Flute", 5, "Sun Microsystems", "./songfiles/flute.aif" ));
		songs.add(new Song("Space Music", 6, "Unknown", "./songfiles/spacemusic.au"));
		songs.add(new Song("Swing Cheese", 15, "FreePlay Music", "./songfiles/SwingCheese.mp3" ));
		songs.add(new Song("Tada", 2, "Microsoft", "./songfiles/tada.wav" ));
		songs.add(new Song("Untameable Fire", 282, "Pierre Langer", "./songfiles/UntameableFire.mp3" ));
		

		


	}
	
	public boolean isSongPlayable(Song song){
		
		if (currentStudent.getId()!=null){
			
			if (song.getPlaysToday()<5){
				
				if (currentStudent.getSongsPlayedToday()<2){
					
					return true;
				}
			}
		}
		
		return false;

		
	}
	

	
//	public boolean Login(String ID, String pass)
//	{
//
//			
//			reader.validate(ID, pass, students);
//
//			validated = students.getStudent(ID).getAuthenticatedStatus();
//
//			if (validated==true){
//				currentStudent = students.getStudent(ID);
//			}
//		
//	}
	
	
	
	
}
