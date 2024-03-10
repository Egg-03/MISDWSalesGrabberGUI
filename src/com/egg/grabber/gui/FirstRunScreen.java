package com.egg.grabber.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

class FirstRunScreen extends JFrame{
	private static final long serialVersionUID = -7564087109447108908L;
	private static FirstRunScreen firstRunInstance = null;
	
	public static FirstRunScreen getInstance() {
		if(firstRunInstance==null)
			firstRunInstance = new FirstRunScreen();
		return firstRunInstance;
	}
	
	private FirstRunScreen() {
		super("First Run Screen");
		getContentPane().setLayout(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(700,262);
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "FIRST RUN INSTRUCTIONS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 664, 189);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel inst1 = new JLabel("1) Make sure you have the appropriate drivers downloaded and installed for the program to work correctly");
		inst1.setBounds(10, 31, 604, 14);
		panel.add(inst1);
		
		JLabel inst2 = new JLabel("2) Your browser drivers should be placed in the Downloads folder for the program to work correctly.");
		inst2.setBounds(10, 68, 604, 14);
		panel.add(inst2);
		
		JLabel inst3 = new JLabel("3) To use the program, set a date range and click on the \"Fetch\" button. \r\n");
		inst3.setBounds(10, 105, 514, 14);
		panel.add(inst3);
		
		JLabel inst3_2 = new JLabel("Upon a successful connection, your files will be automatically downloaded and stored in your Downloads folder.");
		inst3_2.setBounds(10, 120, 644, 14);
		panel.add(inst3_2);
		
		JButton confirm = new JButton("GOT IT!");
		confirm.setBounds(296, 197, 83, 15);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
				GrabberGUI g = GrabberGUI.getInstance();
				g.setVisible(true);
			}
		});
		getContentPane().add(confirm);
	}
}