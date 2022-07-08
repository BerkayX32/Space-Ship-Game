package fighter;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;


import javax.swing.JOptionPane;

public class Launcher extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	public static String scoreTable = "ScoreTable.txt";
	public static int screenWidth = 1280;
	public static int screenHeight = 720;
	
public static void main(String[] args) {
	
		JFrame frame = new JFrame("Jet Fighter");
		JButton play;
		JButton register;
		JButton exit;
		JButton login;
		JButton scoreboard;
		
		play = new JButton("Start");
		exit = new JButton("Exit");
		login = new JButton("Login");
		register = new JButton("Register");
		scoreboard = new JButton("scoreboard");
			
		frame.add(play);
		frame.add(register);
		frame.add(login);
		frame.add(scoreboard);
		frame.add(exit);
		frame.setLayout(new FlowLayout());
		frame.setSize(500,500);  
		frame.setVisible(true);  
		frame.setLocationRelativeTo(null);
	
		// redirects to login popup
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				Login in = new Login();
				in.initScreenComponents();
			}	
		});
		
		// creates record into txt file
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				Register register = new Register();
				register.initComponents();
			}	
		});
		
		// same action as play opens login up and ask for user info if success starts the game
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				Login in = new Login();
				in.initScreenComponents();
			}	
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}	
		});
		
		scoreboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String scoreTableRecords = "";
				try {
					//read file for user line by line
					Scanner in = new Scanner(new File(scoreTable));
					while (in.hasNextLine()){
						scoreTableRecords += in.nextLine();
						scoreTableRecords += "\n";
					}
				} catch (IOException e1) {
				}
				JOptionPane.showMessageDialog(null,scoreTableRecords);
			}
		});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}