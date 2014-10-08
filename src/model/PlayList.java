package model;

import java.util.ArrayList;
import java.util.Queue;





import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class PlayList {

	ArrayList<Song> playlist;
	
	
	public PlayList(){
		
		playlist = new ArrayList<Song>();
	}
	
	public void queueUpNextSong(Song song){
		playlist.add(song);
		
	}
	
}