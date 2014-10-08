package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Song;
import model.SongCollection;


public class JukeboxGUI extends JFrame{

	private JTable table;
	private SongCollection songs;
	
	public JukeboxGUI(){
		songs = new SongCollection();
		songs.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3" ));
		songs.add(new Song("Determined Tumbao", 20, "FreePlay Music", "./songfiles/DeterminedTumbao.mp3" ));
		songs.add(new Song("Flute", 5, "Sun Microsystems", "./songfiles/flute.aif" ));
		songs.add(new Song("Space Music", 6, "Unknown", "./songfiles/spacemusic.au"));
		songs.add(new Song("Swing Cheese", 15, "FreePlay Music", "./songfiles/SwingCheese.mp3" ));
		songs.add(new Song("Tada", 2, "Microsoft", "./songfiles/tada.wav" ));
		songs.add(new Song("Untameable Fire", 282, "Pierre Langer", "./songfiles/UntameableFire.mp3" ));
		
		table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		
		this.setLayout(new GridLayout(1,1));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JLabel label = new JLabel("Select a Song from this Jukebox");
		panel.add(label, BorderLayout.NORTH);
		
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}
	
}
