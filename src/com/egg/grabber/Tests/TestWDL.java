package com.egg.grabber.Tests;

import com.egg.grabber.main.WebDriverLauncher;

public class TestWDL {

	public static void main(String[] args)  {
		WebDriverLauncher wdl;
		try {
			wdl = new WebDriverLauncher("C:\\Users\\Egg-03\\Downloads\\chromedriver.exe", "C:\\Users\\Egg-03\\Downloads");
			wdl.dateWiseSales("2023", "2", "2", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
