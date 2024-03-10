package com.egg.grabber.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;

import javax.swing.JFrame;
import com.egg.grabber.customExceptions.ExceptionUI;

// REWRITE THIS ENTIRE THING OMG THIS IS TRASHHH

public class Starter{
	public static void main(String args[]) {
		try{
			File f = new File(System.getProperty("user.home")+"\\AppData\\Local\\MISDWFirstRun.ini");
			if(f.exists() && !f.isDirectory()) { //if the file exists and is not a directory
				try(FileInputStream fis = new FileInputStream(System.getProperty("user.home")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					byte data[] = new byte[fis.available()];
					fis.read(data);
					String uname = new String(data); //try to read data from file
					if(uname.matches(System.getProperty("user.name"))) { //if data matches username, then launch
						new Thread(() -> {
							//imported from com.egg.grabber.Tests.SingleInstanceUsingSockets
							try(ServerSocket ss = new ServerSocket()){
								ss.bind(new InetSocketAddress(42069));
								GrabberGUI g = GrabberGUI.getInstance();
								g.setVisible(true);
								Thread.currentThread().join();
							} catch (SocketException se) {
								ExceptionUI ecp = new ExceptionUI("An instance of this application is already running. Please close the previous instance to start a new instance.");
								ecp.setVisible(true);
							} catch (Exception e) {
								ExceptionUI ecp = new ExceptionUI("An unknown error has occured with the following code : SERVER_SOCKET_ERROR");
								ecp.setVisible(true);
							}
							
						}).start();
					}
					else { //this is when the file exists but has the incorrect username
						try(FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"\\AppData\\Local\\MISDWFirstRun.ini");){
							fos.write(System.getProperty("user.name").getBytes());
							
							new Thread(()-> {
								FirstRunScreen frs = FirstRunScreen.getInstance();
								frs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							}).start();
						}
						catch(Exception e) {
							ExceptionUI ecp = new ExceptionUI(e.getMessage());
							ecp.setVisible(true);
						}
					}
				  }catch(Exception e) {
					  ExceptionUI ecp = new ExceptionUI(e.getMessage());
					  ecp.setVisible(true);
				  }
				}
			else {//this is for when the file does not exist
				try(FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"\\AppData\\Local\\MISDWFirstRun.ini");){
					fos.write(System.getProperty("user.name").getBytes());
					new Thread(()-> {
						FirstRunScreen frs = FirstRunScreen.getInstance();
						frs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}).start();
				} catch (Exception e) {
					ExceptionUI ecp = new ExceptionUI(e.getMessage());
					ecp.setVisible(true);
				}
			}
				
		}catch(Exception e) {
			ExceptionUI ecp = new ExceptionUI(e.getMessage());
			ecp.setVisible(true);
		}
	}
}
