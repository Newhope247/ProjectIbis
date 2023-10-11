package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDataWithErrorMessage {
	

	WebDriver driver;

	@DataProvider(name = "data-set")
	public static Object[][] DataSet() {
		
		Object[][] obj = {
				{"valid","test_ibisvision","ibisvision123!"},
				{"invalid","ibisvision","qwerty11"},
		        };
		    return obj;
	}
	@Test(dataProvider = "data-set")
	public void verifyLoginCredentials(String type, String username, String password){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\hp\\eclipse-workspace\\IbisConnect\\Resources\\geckodriver.exe");
	    System.out.println(type + " " + username + " " + password);
		driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);	 
		
		driver.get("https://staging-ibisconnect.ibisvision.co.uk/login");
        
		  
	    driver.findElement(By.id("login_username")).sendKeys(username);
	    driver.findElement(By.id("login_password")).sendKeys(password);
	    driver.findElement(By.id("login-btn")).click();
	    
	    if(type.equals("valid")) {
			   String expected_title = "IBIS-Connect";
			    String actual_title = driver.getTitle();
			    Assert.assertEquals(expected_title, actual_title);
			    
		   }else if(type.equals("invalid")) {
			   String errorMessage =  driver.findElement(By.xpath("//div[@role='alert']")).getText();
			   String expected_title = "Invalid credentials!";
			   String actual_title = driver.getTitle();
			   Assert.assertEquals(expected_title, "Invalid credentials!");
		   
		    
		    driver.quit();
	
	}

	}
	
}
		    
		   
