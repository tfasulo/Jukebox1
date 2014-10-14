/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Song Collection is an ArrayList of songs.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements /*ListModel<Song>,*/ TableModel{
	
	private ArrayList<Song> songs;
	//private LinkedList<ListDataListener> listDataListeners;
	private LinkedList<TableModelListener> tableModelListeners;
	private int columnCount = 3;
	
	public SongCollection(){

		songs = new ArrayList<Song>();
		//listDataListeners = new LinkedList<ListDataListener>();
		tableModelListeners = new LinkedList<TableModelListener>();
	}
	
	public void add(Song song) {

		songs.add(song);
		update();
	}

	public void remove(Song song) {

		songs.remove(song);
		update();
	}
	public ArrayList<Song> getArrayList(){
		return songs;
	}
	
	private void update(){
		
//		for(ListDataListener ldl : listDataListeners){
//			ldl.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, songs.size()));
//		}
//		for(TableModelListener tml : tableModelListeners){
//			tml.tableChanged(new TableModelEvent(this));
//		}
	}

//	@Override
//	public void addListDataListener(ListDataListener l) {
//		
//		listDataListeners.add(l);
//	}
//
//	@Override
//	public Song getElementAt(int index) {
//
//		if(index < 0 || index > songs.size()){
//			return null;
//		}
//		
//		return songs.get(index);
//	}
//
//	@Override
//	public int getSize() {
//
//		return songs.size();
//	}
//
//	@Override
//	public void removeListDataListener(ListDataListener l) {
//
//		listDataListeners.remove(l);
//	}

	@Override
	public void addTableModelListener(TableModelListener l) {

//		tableModelListeners.add(l);
	}

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

	@Override
	public int getColumnCount() {

		return columnCount;
	}

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

	@Override
	public int getRowCount() {

		return songs.size();
	}

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

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

		tableModelListeners.remove(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
}
