package com.egg.grabber.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

class AboutFrame extends JFrame {
	private static final long serialVersionUID = -3436104469150169339L;
	private static AboutFrame aboutInstance = null;
	
	//makes this a singleton class so that users cannot spawn multiple instance of this frame
	public static AboutFrame getInstance() {
		if(aboutInstance==null)
			aboutInstance = new AboutFrame();
		return aboutInstance;
	}
	
	private AboutFrame() {
		super("About The Program");
		setSize(493,170);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel aboutBorder = new JPanel();
		aboutBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "About", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		aboutBorder.setBounds(10, 11, 457, 103);
		getContentPane().add(aboutBorder);
		aboutBorder.setLayout(null);
		
		JTextArea description = new JTextArea();
		description.setBounds(10, 21, 437, 71);
		aboutBorder.add(description);
		description.setWrapStyleWord(true);
		description.setLineWrap(true);
		description.setText("A Free and Open Source (FOSS) GUI Toolkit programmed to use Selenium WebDriver to grab daily, monthly and yearly Sales Data from Remedy Medical Services' Online Management Platform");
		description.setBackground(new Color(240, 240, 240));
	}
}
