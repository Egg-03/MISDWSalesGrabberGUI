package com.egg.grabber.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFrame;

public class Starter{
	public static void main(String args[]) {
		try{
			File f = new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");
			if(f.exists() && !f.isDirectory()) { //if the file exists and is not a directory
				try(FileInputStream fis = new FileInputStream("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					byte data[] = new byte[fis.available()];
					fis.read(data);
					String uname = new String(data); //try to read data from file
					if(uname.matches(System.getProperty("user.name"))) { //if data matches username, then launch
						new Thread(() -> {
							GrabberGUI g = GrabberGUI.getInstance();
							g.setVisible(true);
						}).start();
					}
					else { //this is when the file exists but has the incorrect username
						try(FileOutputStream fos = new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");){
							fos.write(System.getProperty("user.name").getBytes());
							
							new Thread(()-> {
								FirstRunScreen frs = FirstRunScreen.getInstance();
								frs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							}).start();
						}
					}
				  }
				}
			else {//this is for when the file does not exist
				try(FileOutputStream fos = new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					fos.write(System.getProperty("user.name").getBytes());
					new Thread(()-> {
						FirstRunScreen frs = FirstRunScreen.getInstance();
						frs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}).start();
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
