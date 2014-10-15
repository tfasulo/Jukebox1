/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Student holds the student information.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;



public class Student implements Serializable{
	
	private String password;
	private String id;
	private int secondsLeft;
	private int songsPlayedToday;
	private boolean loggedIn;
	private GregorianCalendar lastPlayed = new GregorianCalendar();
	
	public Student(String id, String password){
		
		this.id = id;
		this.password=password;
		this.secondsLeft = (1500*60);
		this.songsPlayedToday = 0;
		this.loggedIn = false;
	}
	
	public String getId(){
		
		return id;
	}
	
	public int getSecondsLeft(){
		
		return secondsLeft;
	}
	
	public int getSongsPlayedToday(){
		
		return songsPlayedToday;
	}
	
	public void decrementSecondsLeft(int seconds){
		
		secondsLeft = secondsLeft - seconds;
	}
	
	public void updateSongsPlayedToday(){
		
		songsPlayedToday++;
	}
	
	public String getPassword(){
		
		return password;
	}
	
	public void setLoggedIn(boolean status){
		
		loggedIn = status;
	}
	
	public boolean getAuthenticatedStatus(){
		
		return loggedIn;
	}

	public void playSong(Song song, GregorianCalendar dateplayed, SongCollection songs, StudentCollection students) {
		
		if (dateplayed.get(Calendar.YEAR)==lastPlayed.get(Calendar.YEAR) 
			&& dateplayed.get(Calendar.MONTH)==lastPlayed.get(Calendar.MONTH)
			&& dateplayed.get(Calendar.DAY_OF_MONTH)==lastPlayed.get(Calendar.DAY_OF_MONTH)){
			
		}
		else{
			for (Song s : songs.getArrayList()){
				s.resetPlays();
			}
			Collection<Student> collection;
			collection = students.values();
			Iterator itr = (Iterator) collection.iterator();
			while (itr.hasNext()){
				Student st = (Student) itr.next();
				st.resetPlays();
				st.lastPlayed = dateplayed;
			}
			
			
		}
		
		if (songsPlayedToday<2)
		{
			if (song.getPlaysToday()<5)
			{

				
				songsPlayedToday++;
				song.played();
				secondsLeft-=song.getTime();
				
			}
		}
		
	}
	
	public void resetPlays(){
		songsPlayedToday=0;
	}
}
