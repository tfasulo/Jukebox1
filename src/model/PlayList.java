/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: PlayList is an ArrayDeque of songs added to the queue, and plays the songs.
 */
package model;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentLinkedQueue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class PlayList {

	private ConcurrentLinkedQueue<Song> playlist;
	GregorianCalendar lastPlayed;
	GregorianCalendar currentPlay;
	GregorianCalendar timePlayed;
	ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

	public PlayList(){
		
		playlist = new ConcurrentLinkedQueue<Song>();
	}
	

	public void queueUpNextSong(Song song){
		
			if (playlist.peek()==null){
				SongPlayer.playFile(waiter, song.getFileName());
			}
			playlist.add(song);
			
			
	}
	
	public void play(){
		

	}
	
	  private class ObjectWaitingForSongToEnd implements EndOfSongListener {

		    public void songFinishedPlaying(EndOfSongEvent eosEvent) {
		    	
		    	playlist.poll();
		    	if (playlist.peek()!=null){
		    		SongPlayer.playFile(waiter, playlist.peek().getFileName());
		    	}
//		      System.out.print("Finished " + eosEvent.fileName());
//		      GregorianCalendar finishedAt = eosEvent.finishedTime();
//		      System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
//		          + finishedAt.get(Calendar.MINUTE) + ":"
//		          + finishedAt.get(Calendar.SECOND));
		    }
		  }



}


