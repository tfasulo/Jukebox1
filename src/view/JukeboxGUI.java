/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: JukeboxGUI lays out a Jframe that has a tableview
 * of songs and a login/out panel.
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

	public static void main(String[] args){
		
		new JukeboxGUI();
		
	}

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
	private JScrollPane queue = new JScrollPane();
	private PlayList playList = new PlayList();
	private JTextArea queueText = new JTextArea();
	private GregorianCalendar currentDate = new GregorianCalendar();

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
	
	public void setupTable(){

		
		
		table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
	
		panel.setLayout(new BorderLayout());
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JLabel label = new JLabel("Select a Song from this Jukebox");
		panel.add(label, BorderLayout.NORTH);
		
		songsPlaying.setLayout(new BorderLayout());
		songsPlaying.add(new JLabel("PlayList (song at top is playing)"), BorderLayout.NORTH);
//		queue.add(queueText);
		songsPlaying.add(queueText, BorderLayout.CENTER);
		
		this.setLayout(new GridLayout(1,2,10,0));
		this.add(panel);
		this.add(songsPlaying);
	}
	
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
	public boolean isSongPlayable(Song song){
		
		boolean isPlayable = false;
		
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
	

	
	public void addActionListeners(){
		login.addActionListener(new LoginListener());
		logout.addActionListener(new LogoutListener());
		table.addMouseListener(new ClickListener());
		this.addWindowListener(new SaveDataListener());
	}
	
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
		}
	}
	
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
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
		

	}
	
	
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
	
	public boolean loadData(){
		try{
			FileInputStream inStream = new FileInputStream(new File("jukeboxdata.dat"));
			ObjectInputStream inObject = new ObjectInputStream(inStream);
			songs = (SongCollection)inObject.readObject();
			students = (StudentCollection)inObject.readObject();
			inObject.close();
		}
		catch(Exception e){
			//errorLabel.setText("Unable to load data");
			return false;
		}
		return true;
	}
	

	private class SaveDataListener implements WindowListener{



		@Override
		public void windowActivated(WindowEvent arg0) {

		}

		@Override
		public void windowClosed(WindowEvent arg0) {

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			int answer = JOptionPane.showConfirmDialog(null, "Save Data?", "Save Data?", JOptionPane.YES_NO_OPTION);
			if (answer==JOptionPane.YES_OPTION){
				saveData();
			}

		}



		@Override
		public void windowDeactivated(WindowEvent arg0) {

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {

		}

		@Override
		public void windowIconified(WindowEvent arg0) {

		}

		@Override
		public void windowOpened(WindowEvent arg0) {

		}
	}
	

	
	
	
	
}
