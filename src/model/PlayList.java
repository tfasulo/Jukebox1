package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class PlayList {

	ArrayDeque<Song> playlist;
	GregorianCalendar lastPlayed;
	GregorianCalendar currentPlay;
	GregorianCalendar timePlayed;

	public PlayList(){
		
		playlist = new ArrayDeque<Song>();
	}
	

	public void queueUpNextSong(Song song){
		
			playlist.add(song);
			SongPlayer.playFile(song.getFileName());
			song.played();
	}
	
	public void play(){
	
	}


}


