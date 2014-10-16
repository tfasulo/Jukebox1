/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: PlayList is a concurrentLinkedQueue of songs. It can add songs to the queue and pops them off after they are finished.
 * This class also contains a toString method to print out the name, artist, and length of every song that is currently in the queue.
 */

package model;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class PlayList {

	//global variables for playList
	
	private ConcurrentLinkedQueue<Song> playlist;
	GregorianCalendar lastPlayed;
	GregorianCalendar currentPlay;
	GregorianCalendar timePlayed;
	ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
	
	//constructor for playList that creates a new concurrentLinkedQueue
	
	public PlayList(){
		
		playlist = new ConcurrentLinkedQueue<Song>();
	}
	
	/*This method takes a song as a parameter and adds that song to the queue. If
	 *the queue is empty then it will play the song that was passed in.
	 */

	public void queueUpNextSong(Song song){
		
			if (playlist.peek()==null){
				
				SongPlayer.playFile(waiter, song.getFileName());
			}
			playlist.add(song);
	}
	
	public void play(){}

	/*This pops the song off the queue at the end of the song. After popping the current song
	 *that just finished playing off the queue, it checks to see if there is a next song. If there
	 *is a next song, then it plays that song, if not, it waits for another end of the song when 
	 *another song is added.
	 */
	
	public class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {

			playlist.poll();
			
			if (playlist.peek()!=null){
				SongPlayer.playFile(waiter, playlist.peek().getFileName());
			}
		}
	}
	
	/*This is the toString method which iterates through the queue and returns a string of 
	 *every song that is in the queue in the format of song name, song artist, and then song length.
	 */

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