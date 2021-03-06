package com.mk.Onchip;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Base {

	static Properties prop=new Properties();
	
	static {
		 try {
			
				
				File f=new File(".//Input//config.properties");
				
				FileInputStream fis=new FileInputStream(f);
				
				prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	
	public static void senddata(WebDriver driver,String xpat,String propvalue) throws Exception
	{	 		
		driver.findElement(By.xpath(xpat)).sendKeys(prop.getProperty(propvalue));
	}
	
	public static void calenderclick(WebDriver driver,String xpat,String date)
	{
		List<WebElement> l1=driver.findElements(By.xpath(xpat));
		
		for(int i=0;i<l1.size();i++)
		{
			List<WebElement> l2=driver.findElements(By.tagName("td"));
			
			for(int j=0;j<l2.size();j++)
			{
				WebElement ele=l2.get(j);
				
				String str=ele.getText();
				if(str.equalsIgnoreCase(prop.getProperty(date)))
				{
					ele.click();
					break;
				}
			}
		}
		
	}
	public static void clickonelement(WebDriver driver,String xpat)
	{
		driver.findElement(By.xpath(xpat)).click();
	}
	
	public static void clickondropdown(WebDriver driver,String option,String value)
	{
		List<WebElement> l3=driver.findElements(By.tagName(option));
		
		for(int i=0;i<l3.size();i++)
		{
			WebElement element=l3.get(i);
			
			String str=element.getText();
			
			if(str.equalsIgnoreCase(prop.getProperty(value)))
			{
				element.click();
				
				break;
			}
			
		}
		
	}
	
	public static void clickonradio(WebDriver driver,String xpat,String buttonname)
	{
		List<WebElement> l4=driver.findElements(By.xpath(xpat));
		
		for(int i=0;i<l4.size();i++)
		{
			WebElement element=l4.get(i);
			
			String str=element.getAttribute("value");
			
			if(str.equalsIgnoreCase(prop.getProperty(buttonname)))
			{
			
				element.click();
				
				break;
			}
		}
	}
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(prop.getProperty(fileLocation));
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
}
