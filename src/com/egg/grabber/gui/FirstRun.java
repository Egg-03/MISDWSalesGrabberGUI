package com.egg.grabber.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class FirstRunScreen extends JFrame{
	private static final long serialVersionUID = -7564087109447108908L;
	
	public FirstRunScreen() {
		super("First Run Screen");
		setLayout(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(500,300);
		setVisible(true);
		
		JTextArea txtrFirstRunInstructions = new JTextArea();
		txtrFirstRunInstructions.setLineWrap(true);
		txtrFirstRunInstructions.setWrapStyleWord(true);
		txtrFirstRunInstructions.setText("                  FIRST RUN INSTRUCTIONS\r\n\r\n1) Make sure you have the appropriate drivers downloaded and installed for the program to work correctly\r\n\r\n2) Your browser drivers should be placed in the Downloads folder for the program to work correctly.\r\n\r\n3) To use the program, set a date range and click on the \"Fetch\" button. Upon a successful connection, your files will be automatically downloaded and stored in your Downloads folder.");
		txtrFirstRunInstructions.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrFirstRunInstructions.setBackground(new Color(192, 192, 192));
		txtrFirstRunInstructions.setEditable(false);
		txtrFirstRunInstructions.setBounds(10, 11, 464, 220);
		add(txtrFirstRunInstructions);
		
		JButton confirm = new JButton("GOT IT!");
		confirm.setBounds(204, 235, 83, 15);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
				GrabberGUI g = new GrabberGUI();
				g.setSize(450, 355);
				g.setVisible(true);
				g.setResizable(false);
				g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		add(confirm);
	}
}

public class FirstRun {

	public static void main(String[] args) {
		try{
			File f = new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");
			if(f.exists() && !f.isDirectory()) {
				try(FileInputStream fis = new FileInputStream("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					byte data[] = new byte[fis.available()];
					fis.read(data);
					String uname = new String(data);
					if(uname.matches(System.getProperty("user.name"))) {
						GrabberGUI g = new GrabberGUI();
						g.setSize(450, 355);
						g.setVisible(true);
						g.setResizable(false);
						g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				  }
				}
			else {
				try(FileOutputStream fos = new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					fos.write(System.getProperty("user.name").getBytes());
				}

				FirstRunScreen frs = new FirstRunScreen();
				frs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
