package newpackage;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class myclass {
	
	 public static String driverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
	 public static WebDriver driver;
	 @Test
	public static void main(String[] args) throws InterruptedException {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Wes\\eclipse-workspace\\SeleniumTest\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://www.google.com");
		driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("test");
		driver.findElement(By.xpath("//*[@id='tsf']")).submit();
		
		Thread.sleep(2000);
		
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//*[@id=\'swml-src\']")).getText().contains("From your Internet address"));
		
		WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

	    List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

	    //Get the url of third link and navigate to it
	    String third_link = findElements.get(2).getAttribute("href");
	    driver.navigate().to(third_link);

	    Thread.sleep(2000);
	    
	    //String actualString = driver.findElement(By.xpath("")).getText(); 
		//Assert.assertTrue(actualString.contains("Looking for a test?"));
	    
	    String actualString = driver.findElement(By.xpath("/html/body/article/div[@class='main']/div[@class='row']/div[@class='small-12 columns']/section[@class='tests']/div[@class='row']/div[@class='small-12 columns']/h3")).getText(); 
		AssertJUnit.assertTrue(actualString.contains("Looking for a test?"));
	    
		driver.close();

    }
}
