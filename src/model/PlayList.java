package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
		
		ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
		
		if (song.getPlaysToday()<5){
			playlist.add(song);
			SongPlayer.playFile(waiter, song.getFileName());
			song.played();
		}
		else{
			System.out.println("This song has already been played 5 times today");
		}
	}

	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.print("Finished " + eosEvent.fileName());
			GregorianCalendar finishedAt = eosEvent.finishedTime();
			System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
					+ finishedAt.get(Calendar.MINUTE) + ":"
					+ finishedAt.get(Calendar.SECOND));
		}
	}
}