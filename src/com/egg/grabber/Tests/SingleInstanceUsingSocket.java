package com.egg.grabber.Tests;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class DefFrame extends JFrame{
	private static DefFrame instance;
	
	static {
		instance = new DefFrame();
	}
	
	public static DefFrame getInstance() {
		return instance;
	}
	
	private DefFrame() {
		super("Frame"+DefFrame.class.hashCode());
		setLayout(null);
		setSize(400,300);
		setVisible(true);
	}
	
}

public class SingleInstanceUsingSocket {

	public static void main(String[] args) {
		//Using socket connection to prevent spawning multiple instances
		try (ServerSocket ss = new ServerSocket())
        {
            ss.bind(new InetSocketAddress(42069));
            DefFrame d = DefFrame.getInstance();
    		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println("Application started");
            System.out.println(System.getProperty("user.home"));
            Thread.currentThread().join(); //will make the thread wait until the JVM is killed
        }
        catch (SocketException e)
        {
            System.out.println("Application already running");
            System.exit(1);
        }
        catch(Exception e)
        {
            System.out.println("Application encountered some problem.");
            System.exit(1);
        }
		
	}
}
