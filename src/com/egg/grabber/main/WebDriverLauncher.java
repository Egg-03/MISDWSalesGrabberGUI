package com.egg.grabber.main;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverLauncher {
	private WebDriver driver;
	
	public WebDriverLauncher(String driverPath, String downloadPath) throws Exception {
		System.setProperty("webdriver.chrome.driver", driverPath);
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("safebrowsing.enabled", true);
		chromePrefs.put("download.default_directory", downloadPath);
		chromePrefs.put("download.prompt_for_download", false);
		
		ChromeOptions coption = new ChromeOptions();
		coption.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(coption);
	}
	
	public void dateWiseSales(String year, String month, String start, String end) throws Exception {
		driver.navigate().to("https://remedy.onlinehealthcaremanagement.com/mis/DateWiseSalesReport/0/0/0");
		int gap = Integer.valueOf(end)-Integer.valueOf(start)+1;
		
		for(int i=1 ; i<=gap ; i++) {
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