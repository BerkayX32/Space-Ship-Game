package fighter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {

	//file location and definition
	private static final String filepath="C:\\Users\\onur\\Desktop\\obj";
	File f = new File(filepath);

	//gets line numbers of txt login info
	private static int lineNumbers;

	//local variables
	String _username; 
	String _password; 
	String _alias;

	//Frame definitions
	JFrame frame;
	JPanel buttonPanel;
	JPanel fieldsPanel;
	JLabel username, password, alias;
	JTextField usernameField, passwordField, aliasField;
	JButton register, clear;

	//default const//
	public Register() {

	}

	//create jframe and add componenets
	public void initComponents() {
		frame = new JFrame("Register");
		buttonPanel = new JPanel();
		frame.setPreferredSize(new Dimension(350, 285));
		fieldsPanel = new JPanel();
		username = new JLabel("User Name");
		password = new JLabel("Password");
		alias = new JLabel("Alias");
		usernameField = new JTextField("");
		passwordField = new JTextField("");
		aliasField = new JTextField("");
		register = new JButton("Register");
		clear = new JButton("Clear");
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.setLayout(new FlowLayout());
		fieldsPanel.add(username);
		fieldsPanel.add(usernameField);
		fieldsPanel.add(password);
		fieldsPanel.add(passwordField);
		fieldsPanel.add(alias);
		fieldsPanel.add(aliasField);
		buttonPanel.add(register);
		buttonPanel.add(clear);
		frame.add(fieldsPanel, BorderLayout.PAGE_START);
		frame.add(buttonPanel, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		//clear button onpressed
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}          
		});

		//register button onpressed
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// validation incoming values from screen
				if(!(usernameField.getText().length()>0)) {
					JOptionPane.showMessageDialog(null, "Username will not be empty");
					return;
				}else if(!(passwordField.getText().length()>0)) {
					JOptionPane.showMessageDialog(null, "Password will not be empty");
					return;
				}else if(!(aliasField.getText().length()>0)) {
					JOptionPane.showMessageDialog(null, "Alias will not be empty");
					return;
				}
				saveUser(usernameField.getText(), passwordField.getText(), aliasField.getText());
			}          
		});
	}

	//to create folder if not exist
	void createFolder(){
		if(!f.exists()){
			f.mkdirs();
		}
	}

	//creates user login info into given file
	void saveUser(String userN,String passW,String als){
		//calling countRecords method here provides to save record end of the file  
		countRecords();
		try {
			RandomAccessFile raf = new RandomAccessFile(filepath+"\\logins.txt", "rw");
			for(int i=0;i<lineNumbers;i++){
				raf.readLine();
			}
			raf.writeBytes(userN+ ";"+passW+";"+als+"\r\n");
			JOptionPane.showMessageDialog(null, "Successfully Registered");
			this.clear();
		} catch (IOException ex) {
		}
	}

	// to add user login record end of the file 
	void countRecords(){
		try {
			lineNumbers=0;
			RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
			for(int i=0;raf.readLine()!=null;i++){
				lineNumbers++;
			}
		} catch (IOException ex) {
		}
	}

	//clears jframe fields if needed
	void clear(){
		usernameField.setText("");
		passwordField.setText("");
		aliasField.setText("");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {

		//case "register":register();break;
		//case "clear":clear();break;
		}

	}

}
