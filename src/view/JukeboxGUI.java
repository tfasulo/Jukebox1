/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: JukeboxGUI lays out a JFrame that contains a tableview
 * of songs, a login/out panel, and a playList of all the songs in the queue.
 * The GUI also contains functions to load and save the data so that it is persistent
 * upon window closing. Finally, it contains all the action listeners/mouselisteners
 * for clicking on a button such as log in/log out or playing a song by clicking on it
 * through the table.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.CardReader;
import model.PlayList;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;

public class JukeboxGUI extends JFrame{

	//This is the main method to create a new instance of our JukeboxGUI.
	
	public static void main(String[] args){
		
		new JukeboxGUI();
	}

	//Here is where we define all our global variables.
	
	private JTable table;
	private SongCollection songs;
	private JPanel panel = new JPanel();
	private JPanel authScreen = new JPanel();
	private JPanel songsPlaying = new JPanel();
	private JButton login = new JButton("Login");
	private JButton logout = new JButton("Sign Out");
	private JTextField nametext = new JTextField("");
	private JTextField passtext = new JPasswordField();
	private StudentCollection students;
	private Student currentStudent = null;
	private CardReader reader = new CardReader();
	boolean validated = false;
	private JLabel name = new JLabel("Account Name");
	private JLabel password = new JLabel("Password");
	private JLabel status = new JLabel("Status");
	private JLabel state = new JLabel("Please login");
	private JLabel minsLeft = new JLabel("");
	private JLabel playsLeft = new JLabel("");
	private PlayList playList = new PlayList();
	private JTextArea queueText = new JTextArea();
	private GregorianCalendar currentDate = new GregorianCalendar();

	/*JukeboxGUI() is the constructor for our GUI. It allows the user to load the data that they saved
	 *or to creates a new set of students and songs if data cannot be loaded. It also adds the tables,
	 *authentication screen, and sets up all the action listeners.
	 */
	
	public JukeboxGUI(){
		
		int answer = JOptionPane.showConfirmDialog(null, "Load Data?");
		
		if(answer == JOptionPane.NO_OPTION || !loadData()){
			students = new StudentCollection();
			students.addStudent("Ali", new Student("Ali", "1111"));
			students.addStudent("Chris", new Student("Chris", "2222"));
			students.addStudent("River", new Student("River", "3333"));
			students.addStudent("Ryan", new Student("Ryan", "4444"));
			songs = new SongCollection();
			songs.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", "./songfiles/BlueRidgeMountainMist.mp3" ));
			songs.add(new Song("Determined Tumbao", 20, "FreePlay Music", "./songfiles/DeterminedTumbao.mp3" ));
			songs.add(new Song("Flute", 5, "Sun Microsystems", "./songfiles/flute.aif" ));
			songs.add(new Song("Space Music", 6, "Unknown", "./songfiles/spacemusic.au"));
			songs.add(new Song("Swing Cheese", 15, "FreePlay Music", "./songfiles/SwingCheese.mp3" ));
			songs.add(new Song("Tada", 2, "Microsoft", "./songfiles/tada.wav" ));
			songs.add(new Song("Untameable Fire", 282, "Pierre Langer", "./songfiles/UntameableFire.mp3" ));
		}
		
		setupTable();
		setupAuthenticationScreen();
		addActionListeners();
		
		this.setSize(1000,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*setupTable() creates a new sortable JTable of songs to display on the main JFrame. 
	 *It also sets up the playList of all the songs in the queue and adds that to the main JFrame.
	 */
	
	public void setupTable(){

		table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
	
		panel.setLayout(new BorderLayout());
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JLabel label = new JLabel("Select a Song from this Jukebox");
		panel.add(label, BorderLayout.NORTH);
		
		songsPlaying.setLayout(new BorderLayout());
		songsPlaying.add(new JLabel("PlayList (song at top is playing)"), BorderLayout.NORTH);
		songsPlaying.add(queueText, BorderLayout.CENTER);
		
		this.setLayout(new GridLayout(1,2,10,0));
		this.add(panel);
		this.add(songsPlaying);
	}
	
	/* This sets up the Authentication screen such as the log in/log out button.
	 * It also displays the current student who is logged in, how many minutes they have left,
	 * and how many plays they have left for that current day. Finally it adds the screen to
	 * the main JFrame.
	 */
	
	public void setupAuthenticationScreen(){

		authScreen.setLayout(new GridLayout(5,2,0,0));
		authScreen.add(name);
		authScreen.add(nametext);
		authScreen.add(password);
		authScreen.add(passtext);
		authScreen.add(logout);
		authScreen.add(login);
		authScreen.add(status);
		authScreen.add(state);
		authScreen.add(playsLeft);
		authScreen.add(minsLeft);
		panel.add(authScreen, BorderLayout.SOUTH);
	}
	
	/*This method determines whether or not the song is playable. In order to do this, it determines
	 *whether or not the currentStudent logged in has already played 2 songs today. If they have not
	 *already played 2 songs then it determines if the song they want to play has already been played
	 *5 times. If it has not, then it returns true that the song is able to be played. If not, it shows
	 *an error message dialog explaining why the user cannot play that song.
	 */
	
	public boolean isSongPlayable(Song song){
		
		if (currentStudent!=null){
			if (song.getPlaysToday()<5){
				if (currentStudent.getSongsPlayedToday()<2){
					return true;
				}
			}
		}
		
		if (currentStudent==null){
			JOptionPane.showMessageDialog(this, "Login before you can play a song.");
		}
		else if (!(currentStudent.getSongsPlayedToday()<2)){
			JOptionPane.showMessageDialog(this, "You've played too many songs today, go home.");
		}
		else if (!(song.getPlaysToday()<5)){
			JOptionPane.showMessageDialog(this, "This song has been played too many times today, pick another song.");
		}
		
		return false;
	}
	
	//This method adds the action listeners to the specific button/window/table/etc..
	
	public void addActionListeners(){
		
		login.addActionListener(new LoginListener());
		logout.addActionListener(new LogoutListener());
		table.addMouseListener(new ClickListener());
		this.addWindowListener(new SaveDataListener());
	}
	
	/*LoginListener sets up the JPanel when a student logs in to display their plays left, minutes left,
	 *and displays their name saying they are logged in. Contains error checking in case the username and
	 *password do not match and displays an error dialog.
	 */
	
	private class LoginListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String ID = nametext.getText();
			String pass= passtext.getText();
			
			validated = reader.validate(ID, pass, students);

			if (validated==true && currentStudent==null){
				state.setText(ID + " is now logged in.");
				currentStudent = students.getStudent(ID);
				minsLeft.setText("Minutes left: " + currentStudent.getSecondsLeft()/60 + " minutes " + currentStudent.getSecondsLeft()%60 + " seconds");
				playsLeft.setText("Plays left: " + (2-currentStudent.getSongsPlayedToday()));
			}
			else if(!validated){
				JOptionPane.showMessageDialog(login, "Invalid username or password.");
			}
		}
	}
	
	/*LogoutListener logs out the current user and allows a different user to log in. It sets
	 *all the labels to empty string and sets the currentStudent to null so you must log in
	 *again before you can start playing more songs.
	 */
	
	private class LogoutListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(currentStudent!=null){
				currentStudent.setLoggedIn(false);
				currentStudent=null;
				state.setText("Please login.");
				minsLeft.setText("");
				playsLeft.setText("");
			}
		}
	}
	
	/*ClickListener adds the song clicked to the queue so that it can be played next.
	 *It also updates the song play count, the students play count, the students minutes
	 *left and updates all the labels to reflect these changes.
	 */
	
	private class ClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int row = table.rowAtPoint(arg0.getPoint());
			Song nextSong = songs.getElementAt(row);
			
			if (isSongPlayable(nextSong)){
				
				currentStudent.playSong(nextSong,currentDate, songs, students);
				playList.queueUpNextSong(nextSong);
				minsLeft.setText("Minutes left: " + currentStudent.getSecondsLeft()/60 + " minutes " + currentStudent.getSecondsLeft()%60 + " seconds");
				playsLeft.setText("Plays left: " + (2-currentStudent.getSongsPlayedToday()));
				queueText.setText(playList.toString());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	/*saveData() writes the current student collection and song collection to an output file
	 *so that the jukeBox is persistent and Ali cannot play 3 songs in one day :P
	 */
	
	public void saveData(){
		
		try{
			FileOutputStream outStream = new FileOutputStream(new File("jukeboxdata.dat"));
			ObjectOutputStream outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(songs);
			outObject.writeObject(students);
			outObject.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*loadData reads in the saved song collection and student collection and sets the current
	 *student collection and song data to the old one, thus the jukeBox maintains its persistence.
	 */
	
	public boolean loadData(){
		
		try{
			FileInputStream inStream = new FileInputStream(new File("jukeboxdata.dat"));
			ObjectInputStream inObject = new ObjectInputStream(inStream);
			songs = (SongCollection)inObject.readObject();
			students = (StudentCollection)inObject.readObject();
			inObject.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Unable to load data.");
			return false;
		}
		
		return true;
	}
	
	/*SaveDataListener is the window listener which prompts the user whether or 
	 *not they want to save their data upon closing the jukeBox. If the user's
	 *answer is yes then it writes the data to a file. If the user's answer is no
	 *then the information for that jukeBox instance is not saved.
	 */

	private class SaveDataListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {}

		@Override
		public void windowClosed(WindowEvent arg0) {}

		@Override
		public void windowClosing(WindowEvent arg0) {
			int answer = JOptionPane.showConfirmDialog(null, "Save Data?", "Save Data?", JOptionPane.YES_NO_OPTION);
			if (answer==JOptionPane.YES_OPTION){
				saveData();
			}
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {}

		@Override
		public void windowDeiconified(WindowEvent arg0) {}

		@Override
		public void windowIconified(WindowEvent arg0) {}

		@Override
		public void windowOpened(WindowEvent arg0) {}
	}
}