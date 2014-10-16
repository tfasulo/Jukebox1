/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Song holds the song information such as the name, artist, fileName, length, and how many times the song has been played on any given day.
 */

package model;

import java.io.Serializable;

public class Song implements Serializable{
	
	//global variables for a song.

	private String name;
	private String artist;
	private String fileName;
	private int timeInSeconds;
	private int playsToday;
	
	//Song constructor that takes in a name, length, artist, and fileName as a parameter.

	public Song(String name, int time, String artist, String fileName){

		this.name = name;
		this.artist = artist;
		this.timeInSeconds = time;
		this.playsToday = 0;
		this.fileName=fileName;
	}
	
	//Basic getters for fileName, song name, song artist, song length, number of times the song has been played today

	public String getFileName(){
		return fileName;
	}

	public String getSongName(){
		return name;
	}

	public String getSongArtist(){
		return artist;
	}

	public int getTime(){
		return timeInSeconds;
	}

	public int getPlaysToday(){
		return playsToday;
	}
	
	//Increments the number of plays for a specific song if a user plays that song.

	public void played(){

		playsToday++;
	}
	
	//resets the number of plays for that day if it becomes a new gregorian calander date.

	public void resetPlays(){
		playsToday=0;
	}
}