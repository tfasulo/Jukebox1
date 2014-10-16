/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Student holds the student information such as the name, password, time left for the semester, songs that they have played today,
 * whether they are logged in or not, and the last date at which they played a song.
 */

package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Student implements Serializable{
	
	//global variables for every student
	
	private String password;
	private String id;
	private int secondsLeft;
	private int songsPlayedToday;
	private boolean loggedIn;
	private GregorianCalendar lastPlayed = new GregorianCalendar();
	
	/*Constructor for a student that takes in a username and password and 
	 *sets everything else to its default value. A student starts with 1500 minutes
	 *of song time and 0 songs played when they are registered.
	 */
	
	public Student(String id, String password){
		
		this.id = id;
		this.password=password;
		this.secondsLeft = (1500*60);
		this.songsPlayedToday = 0;
		this.loggedIn = false;
	}
	
	/*Basic getters that return the students username, time remaining for the semester, 
	* how many songs they played today, their password, and whether or not they are logged in.
	*/
	
	public String getId(){
		
		return id;
	}
	
	public int getSecondsLeft(){
		
		return secondsLeft;
	}
	
	public int getSongsPlayedToday(){
		
		return songsPlayedToday;
	}
	
	public String getPassword(){
		
		return password;
	}
	
	public boolean getAuthenticatedStatus(){
		
		return loggedIn;
	}
	
	//Decrements a students time remaining for the semester by the song length they chose if they played a song.
	
	public void decrementSecondsLeft(int seconds){
		
		secondsLeft = secondsLeft - seconds;
	}
	
	//Increments songsPlayedToday if a student plays a song.
	
	public void updateSongsPlayedToday(){
		
		songsPlayedToday++;
	}
	
	//sets whether or not they are logged in or out based on their authenticationStatus
	
	public void setLoggedIn(boolean status){
		
		loggedIn = status;
	}
	
	/*A student is the person who plays a song. If the song is being played on a different date then everything needs to be
	 *reset such as the students plays per day and the songs plays per day. This method checks to see if the current date
	 *is the same. If it is not, then it iterates through every song and every student and resets their values. If the date is 
	 *the same, then it continues about normally.
	 */

	public void playSong(Song song, GregorianCalendar dateplayed, SongCollection songs, StudentCollection students) {
		
		if (dateplayed.get(Calendar.YEAR)==lastPlayed.get(Calendar.YEAR) 
			&& dateplayed.get(Calendar.MONTH)==lastPlayed.get(Calendar.MONTH)
			&& dateplayed.get(Calendar.DAY_OF_MONTH)==lastPlayed.get(Calendar.DAY_OF_MONTH)){
			//do nothing
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
	
	//resets the songs that a student has played on a given day if the calendar date changes.
	
	public void resetPlays(){
		songsPlayedToday=0;
	}
}
