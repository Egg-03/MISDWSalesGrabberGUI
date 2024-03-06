package com.egg.grabber.gui;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.egg.grabber.main.WebDriverLauncher;

class GrabberGUI extends JFrame{
	private static final long serialVersionUID = -4697220219932240237L;
	
	private JLabel driverPath, dataDownloadPath, startDayLabel, endDayLabel, monthLabel, yearLabel;
	private JTextField driverPathField, dataDownloadPathField;
	private JComboBox<String> startDay, endDay, month, year;
	private JButton fetch;
	private JTextArea dumpExceptionLogs;
	
	public GrabberGUI() {
		super("MISDW Report Grabber v1.01");
		setLayout(null);
		
		addLocations();
		addGrabber();
		addLogger();
	}

	private void addLocations() {
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 414, 2);
		add(separator);
		
		driverPath = new JLabel("Driver Path");
		driverPath.setBounds(10, 26, 67, 14);
		add(driverPath);
		
		dataDownloadPath = new JLabel("Data Download Path");
		dataDownloadPath.setBounds(10, 51, 121, 14);
		add(dataDownloadPath);
		
		driverPathField = new JTextField();
		driverPathField.setEditable(false);
		driverPathField.setBounds(141, 23, 283, 20);
		driverPathField.setColumns(10);
		driverPathField.setText("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\chromedriver.exe");
		driverPathField.setToolTipText("Put the chromedriver.exe file in the shown location");
		add(driverPathField);
		
		dataDownloadPathField = new JTextField();
		dataDownloadPathField.setEditable(false);
		dataDownloadPathField.setBounds(141, 48, 283, 20);
		dataDownloadPathField.setColumns(10);
		dataDownloadPathField.setText("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads");
		add(dataDownloadPathField);
		
		JButton openDownloadPath = new JButton("Open Location");
		openDownloadPath.setBounds(291, 73, 133, 23);
		openDownloadPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads"));
				} catch (IOException e1) {
					dumpExceptionLogs.setText(e1.getMessage());
				}
			}
		});
		add(openDownloadPath);
	}
	
	private void addGrabber() {
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 104, 414, 2);
		add(separator_1);
		
		startDayLabel = new JLabel("Begin Range");
		startDayLabel.setBounds(10, 118, 82, 14);
		add(startDayLabel);
		
		startDay = new JComboBox<>();
		startDay.setEditable(false);
		startDay.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		startDay.setSelectedIndex(0);
		startDay.setBounds(89, 114, 59, 22);
		add(startDay);
		
		endDayLabel = new JLabel("End Range");
		endDayLabel.setBounds(158, 118, 67, 14);
		add(endDayLabel);
		
		endDay = new JComboBox<>();
		endDay.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		endDay.setSelectedIndex(0);
		endDay.setBounds(235, 114, 59, 22);
		add(endDay);
		
		monthLabel = new JLabel("Month");
		monthLabel.setBounds(10, 162, 46, 14);
		add(monthLabel);
		
		month = new JComboBox<>();
		month.setBounds(89, 158, 59, 22);
		month.setMaximumRowCount(4);
		month.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		month.setSelectedIndex(0);
		add(month);
		
		yearLabel = new JLabel("Year");
		yearLabel.setBounds(158, 162, 46, 14);
		add(yearLabel);
		
		year = new JComboBox<>();
		year.setBounds(235, 158, 59, 22);
		year.setModel(new DefaultComboBoxModel<String>(new String[] {String.valueOf(LocalDateTime.now().getYear()), String.valueOf(LocalDateTime.now().getYear()-1), String.valueOf(LocalDateTime.now().getYear()-2)}));
		year.setSelectedIndex(0);
		getContentPane().add(year);
		
		//CALLS THE WEBDRIVER from com.egg.grabber.main.WebDriverLauncher
		fetch= new JButton("Fetch");
		fetch.setBounds(320, 130, 89, 35);
		add(fetch);
		fetch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dumpExceptionLogs.setText(null); //clear the textArea before running the fetch action
				try {
					WebDriverLauncher wbl = new WebDriverLauncher(driverPathField.getText(), dataDownloadPathField.getText());
					wbl.dateWiseSales(year.getSelectedItem().toString(), month.getSelectedItem().toString(), startDay.getSelectedItem().toString(), endDay.getSelectedItem().toString());
				} catch (Exception e1) {
					dumpExceptionLogs.setText(e1.getMessage());
				}
			}
		});
	}
	
	private void addLogger() {
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 187, 414, 3);
		add(separator_2);
		
		dumpExceptionLogs = new JTextArea();
		dumpExceptionLogs.setToolTipText("Logs");
		dumpExceptionLogs.setForeground(new Color(255, 0, 128));
		dumpExceptionLogs.setBackground(new Color(192, 192, 192));
		dumpExceptionLogs.setFont(new Font("Consolas", Font.PLAIN, 13));
		dumpExceptionLogs.setEditable(false);
		
		JScrollPane logPane = new JScrollPane(dumpExceptionLogs);
		logPane.setBounds(10, 201, 414, 94);
		logPane.setAutoscrolls(true);
		logPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(logPane);
	}
}
