package com.egg.grabber.customExceptions;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.Color;

//TO-DO : Make all exceptions display this frame
//all exceptions are going to be displayed by this frame
public class ExceptionUI extends JFrame{
	private static final long serialVersionUID = 5951705399700376822L;
	private JTextArea exceptionArea = new JTextArea();

	public ExceptionUI(String msg) {
		super("MISDWRG Error");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(490,170);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Error", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 454, 109);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		exceptionArea.setLineWrap(true);
		exceptionArea.setEditable(false);
		exceptionArea.setBackground(new Color(240, 240, 240));
		exceptionArea.setBounds(10, 22, 434, 65);
		exceptionArea.setText(msg);
		panel.add(exceptionArea);
	}
}
