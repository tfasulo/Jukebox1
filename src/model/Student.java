package model;

public class Student {
	
	private int password;
	private String id;
	private int secondsLeft;
	private int songsPlayedToday;
	
	public Student(int password){
	
		this.password=password;
		this.secondsLeft = 1500*60;
		this.songsPlayedToday = 0;
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
	
	public int getPassword(){
		return password;
	}
}
