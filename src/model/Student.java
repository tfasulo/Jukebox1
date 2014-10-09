package model;

public class Student {
	
	private String password;
	private String id;
	private int secondsLeft;
	private int songsPlayedToday;
	private boolean loggedIn;
	
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
}
