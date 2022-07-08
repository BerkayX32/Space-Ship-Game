package fighter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Scanner;
import java.io.*;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// define screen res
	public static int screenWidth = 1280;
	public static int screenHeight = 720;

	// define filepath
	private static final String filepath="C:\\Users\\onur\\Desktop\\obj\\logins.txt";

	// jframe components
	JLabel username, password, alias;
	JTextField usernameField, passwordField;
	JFrame frame;
	JPanel buttonPane, fieldsPanel;
	JButton loggedin;

	public void initScreenComponents() {
		frame = new JFrame("Login");
		buttonPane = new JPanel();
		frame.setPreferredSize(new Dimension(350, 285));
		fieldsPanel = new JPanel();
		username = new JLabel("User Name");
		password = new JLabel("Password");
		alias = new JLabel("Alias");
		usernameField = new JTextField("");
		passwordField = new JTextField("");
		loggedin = new JButton("Login");
		buttonPane.add(loggedin);
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
		buttonPane.setLayout(new FlowLayout());
		fieldsPanel.add(username);
		fieldsPanel.add(usernameField);
		fieldsPanel.add(password);
		fieldsPanel.add(passwordField);
		frame.add(fieldsPanel, BorderLayout.PAGE_START);
		frame.add(buttonPane, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		loggedin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User user = getUserIfExist(usernameField.getText(), passwordField.getText());
				if(user != null) {
					Game game = new Game(screenWidth,screenHeight,user.getAlias(),"Play");
					game.start();
				}
			}          
		});
	}

	// searches file given if user record exist and returns User object
	User getUserIfExist(String usr,String pswd){
		//user object as null
		User user = null;
		String userRecord = "";
		String passWordRecord = "";
		String aliasRecord = "";
		try {
			//read file for user line by line
			Scanner in = new Scanner(new File(filepath));
			while (in.hasNextLine()){
				String s = in.nextLine();
				if(s.length() > 0 ) {
					userRecord = s.split(";")[0];
					passWordRecord = s.split(";")[1];
					aliasRecord = s.split(";")[2];
					if(usr.equals(userRecord)& pswd.equals(passWordRecord)) {
						user = new User();
						user.setUserName(userRecord);
						user.setPassword(passWordRecord);
						user.setAlias(aliasRecord);
						return user;
					}
				}
			}
		} catch (FileNotFoundException e) {
		}
		return user;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
