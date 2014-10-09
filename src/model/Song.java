package model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Song{

		private String name;
		private String artist;
		private String fileName;
		private int timeInSeconds;
		private int playsToday;

		
		public Song(String name, int time, String artist, String fileName){
			
			this.name = name;
			this.artist = artist;
			this.timeInSeconds = time;
			this.playsToday = 0;
			this.fileName=fileName;
		}
		
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
		
		public void played(){
			
			playsToday++;
		}
}
