package com.egg.grabber.main;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.egg.grabber.customExceptions.ExceptionUI;

public class WebDriverLauncher {
	private WebDriver driver;
	
	public WebDriverLauncher(String driverPath, String downloadPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("safebrowsing.enabled", true);
		chromePrefs.put("download.default_directory", downloadPath);
		chromePrefs.put("download.prompt_for_download", false);
		
		ChromeOptions coption = new ChromeOptions();
		coption.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(coption);
	}
	
	public void dateWiseSales(String year, String month, String start, String end){
		if(Integer.parseInt(start)>Integer.parseInt(end)) {
			ExceptionUI ecp = new ExceptionUI("Begin Range cannot be greater than End Range. Please close this message and    try again.");
			ecp.setVisible(true);
			driver.quit();
		}
		else {
			driver.navigate().to("https://remedy.onlinehealthcaremanagement.com/mis/DateWiseSalesReport/0/0/0");
			for(int i=Integer.parseInt(start) ; i<=Integer.parseInt(end) ; i++) {
				//fill start date
				driver.findElement(By.id("StartDate")).sendKeys(i+"/"+month+"/"+year);
				//fill end date
				driver.findElement(By.id("EndDate")).sendKeys(i+"/"+month+"/"+year);
				
				//export-to-excel-button
				driver.findElement(By.className("btn-info")).click();
				
				//clear the text fields
				driver.findElement(By.id("StartDate")).clear();
				driver.findElement(By.id("EndDate")).clear();
		    }
		}
	}
}
