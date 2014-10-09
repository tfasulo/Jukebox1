package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.CardReader;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;


public class JukeboxGUI extends JFrame{

	private JTable table;
	private SongCollection songs;
	private JPanel panel = new JPanel();
	private JPanel authScreen = new JPanel();
	private JButton login = new JButton("Login");
	private JButton logout = new JButton("Sign Out");
	private JTextField nametext = new JTextField("");
	private JTextField passtext = new JPasswordField();
	private StudentCollection students = new StudentCollection();
	private Student currentStudent = new Student(null, null);
	private CardReader reader = new CardReader();
	boolean validated = false;
	private JLabel name = new JLabel("Account Name");
	private JLabel password = new JLabel("Password");
	private JLabel status = new JLabel("Status");
	private JLabel state = new JLabel("Login first.");
	
	public JukeboxGUI(){
		
		students.addStudent("Ali", new Student("Ali", "1111"));
		students.addStudent("Chris", new Student("Chris", "2222"));
		students.addStudent("River", new Student("River", "3333"));
		students.addStudent("Ryan", new Student("Ryan", "4444"));
		
		setupTable();
		setupAuthenticationScreen();
		addActionListeners();
		
		this.setSize(600,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	public void setupTable(){
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
	
		panel.setLayout(new BorderLayout());
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JLabel label = new JLabel("Select a Song from this Jukebox");
		panel.add(label, BorderLayout.NORTH);
		

		this.add(panel);
		
	}
	
	public void setupAuthenticationScreen(){

		authScreen.setLayout(new GridLayout(4,2,0,0));
		authScreen.add(name);
		authScreen.add(nametext);
		authScreen.add(password);
		authScreen.add(passtext);
		authScreen.add(logout);
		authScreen.add(login);
		authScreen.add(status);
		authScreen.add(state);
		panel.add(authScreen, BorderLayout.SOUTH);
			
	}
	
//	public boolean isSongPlayable(){
//		if ()
//
//	}
	
	public void addActionListeners(){
		login.addActionListener(new LoginListener());
	}
	
	private class LoginListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = nametext.getText();
			String pass= passtext.getText();
			
			reader.validate(ID, pass, students);

			validated = students.getStudent(ID).getAuthenticatedStatus();

			if (validated==true){
				state.setText(ID + " is now logged in.");
				currentStudent = students.getStudent(ID);
			}
		}
	}
	
	
	
}
