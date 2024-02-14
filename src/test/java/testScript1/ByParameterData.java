package testScript1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ByParameterData {
	@Parameters({"URL"})
	@Test 
	   public  void byParameterMethod(String blazeUrl) {
	    	WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get(blazeUrl);
			String title=driver.findElement(By.xpath("//h1[normalize-space()='Welcome to the Simple Travel Agency!']")).getText();
			String HomepageTitle="Welcome to the Simple Travel Agency!";
			Assert.assertEquals(title,HomepageTitle);
			Reporter.log("This is home page of application");
			
		driver.findElement(By.xpath("//a[normalize-space()='destination of the week! The Beach!']")).click();
		 // Check if a new tab opens with the string 'vacation' in the URL
	        for (String winHandle : driver.getWindowHandles()) {
	            driver.switchTo().window(winHandle);
	            if (driver.getCurrentUrl().contains("vacation")) {
	                System.out.println("New tab with 'vacation' in URL");
	                break;
	            }
	        }
	  

	        // Navigate back to the home page tab
	        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
	        
	        //if new tab is not present then go to main page via parameter
	        driver.get(blazeUrl);
	        
	        WebElement departureCity = driver.findElement(By.name("fromPort"));
	        Select departCity= new Select(departureCity);
	        departCity.selectByVisibleText("Mexico City");
	        
	        WebElement destination = driver.findElement(By.name("toPort"));
	        Select destCity= new Select(destination);
	        destCity.selectByVisibleText("London");
	        driver.findElement(By.cssSelector("input[value='Find Flights']")).click();
	        driver.findElement(By.xpath("(//input[@value='Choose This Flight'])[3]")).click();
	      
	        String purchagePage=driver.getTitle();
	        Assert.assertEquals(purchagePage, "BlazeDemo Purchase");
	        Reporter.log("we are navigated to purchage page");
	        
	       String totalCostField = driver.findElement(By.xpath("//p[text()='Total Cost: ']")).getText();
	        System.out.println(totalCostField);
	        String[] arr = totalCostField.split(" ");
	 
	        String formattedValue = arr[2].replaceAll("[^\\d.]", "");

	        Assert.assertTrue(formattedValue.matches("\\d{3}\\.\\d{2}"), "Value does not match the expected pattern");
	        System.out.println("Formatted Value: " + formattedValue);
	     
	        
	       // Click on the "Purchase flight" button
	       driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
	    String id=   driver.findElement(By.xpath("//table//tbody//tr")).getText();
	    
	       System.out.println(id);
	       
	       //close
	       driver.quit();
	        }
}
