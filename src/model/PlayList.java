/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: PlayList is an ArrayDeque of songs added to the queue, and plays the songs.
 */
package model;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
	
	  public class ObjectWaitingForSongToEnd implements EndOfSongListener {

		    public void songFinishedPlaying(EndOfSongEvent eosEvent) {
		    	
		    	playlist.poll();
		    	if (playlist.peek()!=null){
		    		SongPlayer.playFile(waiter, playlist.peek().getFileName());
		    	}
		    }
		  }
	  
	  public String toString(){
		  	String songString="";
			Iterator itr = (Iterator) playlist.iterator();
			while (itr.hasNext()){
				Song s = (Song) itr.next();
				String seconds = Integer.toString(s.getTime()%60);
				
				if (s.getTime()%60<10){
					seconds="0"+seconds;
				}
				songString+=s.getSongName() + " by " + s.getSongArtist() + " " + s.getTime()/60 + ":" + seconds;
				songString+="\n";
			}
			
			
			
			return songString;
	  }
	  




}


