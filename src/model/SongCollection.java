/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Song Collection is an ArrayList of songs. You can get information regarding the song through the table
 * because it implements tableModel and contains all the methods in order to get values from a table such as getColumnName etc.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel, Serializable{
	
	//the global variables used throughout the song collection
	
	private ArrayList<Song> songs;
	private LinkedList<TableModelListener> tableModelListeners;
	private int columnCount = 3;
	
	/*the constructor for the song collection. It sets the table model listener
	 *to a new listener and songs to a new array list.
	 */

	public SongCollection(){

		songs = new ArrayList<Song>();
		tableModelListeners = new LinkedList<TableModelListener>();
	}
	
	//method used to add a song to the array list and update it in the table. Not used in our jukeBox
	
	public void add(Song song) {

		songs.add(song);
		update();
	}
	
	//method used to remove a song from the array list and update it in the table. Not used in our jukeBox

	public void remove(Song song) {

		songs.remove(song);
		update();
	}
	
	//This method returns a song in the array list
	
	public Song getElementAt(int index){
		
		if (index<0 || index > songs.size()){
			return null;
		}
		else{
			return songs.get(index);
		}
	}
	
	//This method returns every song in the song collection
	
	public ArrayList<Song> getArrayList(){
		return songs;
	}
	
	//Used to update the table but not used in our jukeBox
	
	private void update(){}

	@Override
	public void addTableModelListener(TableModelListener l) {}

	/*This method returns the class of each column row in the table
	 *In this case, it returns a string for both the first and second columns
	 *and returns an integer for the third column.
	 */
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch(columnIndex){
			case 0:
			case 1:
				return String.class;
			case 2:
				return Integer.class;
		}
		return null;
	}
	
	//returns how many columns are in our table, which currently is 3

	@Override
	public int getColumnCount() {

		return columnCount;
	}
	
	/*This method returns the column name of each column row in the table.
	 *The first row contains the "Artist" of the song. The second contains
	 *the "Title" of the song and the final column contains the length of the song.
	 */

	@Override
	public String getColumnName(int columnIndex) {

		switch(columnIndex){
			case 0:
				return "Artist";
			case 1:
				return "Title";
			case 2:
				return "Seconds";
		}
		return null;
	}
	
	//Returns how many songs are in the song collection

	@Override
	public int getRowCount() {

		return songs.size();
	}
	
	/*This method returns the object at a given index and column index.
	 *For example, if its column 1 or 2 then it will return a string which
	 *is either the song name or the artist name. For the third column it 
	 *would return the song length but it only returns those attributes of
	 *the song instead of the whole song.
	 */

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch(columnIndex){
			case 0:
				return songs.get(rowIndex).getSongArtist();
			case 1:
				return songs.get(rowIndex).getSongName();
			case 2:
				return songs.get(rowIndex).getTime();
		}
		return null;
	}
	
	//boolean to determine if the user can edit the cell or not. In this case, no.

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return false;
	}
	
	//A method to remove any table model listeners. Not used in our jukeBox.

	@Override
	public void removeTableModelListener(TableModelListener l) {

		tableModelListeners.remove(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
}
