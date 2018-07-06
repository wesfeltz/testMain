package stepDefinition;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.http.auth.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Test_Steps {
public static WebDriver driver;
	
@Given("^User is on Home Page$")
public void user_is_on_Home_Page() throws Throwable {
			// Create a new instance of the Chrome driver

			driver = new ChromeDriver();

	        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        //Launch the Online Store Website

	        driver.get("http://www.store.demoqa.com");
	        
}

@When("^User Navigate to LogIn Page$")
public void user_Navigate_to_LogIn_Page() throws Throwable {
	// Find the element that's ID attribute is 'account'(My Account) 

    driver.findElement(By.xpath(".//*[@id='account']/a")).click();
    
}

@When("^User enters \"(.*)\" and \"(.*)\"$")
public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
    // Find the element that's ID attribute is 'log' (Username)

    // Enter Username on the element found by above desc.

    driver.findElement(By.id("log")).sendKeys(username); 

    // Find the element that's ID attribute is 'pwd' (Password)

    // Enter Password on the element found by the above desc.

    driver.findElement(By.id("pwd")).sendKeys(password);

    // Now submit the form. WebDriver will find the form for us from the element 

    driver.findElement(By.id("login")).click();
    
}

@Then("^Message displayed Login Successfully$")
public void message_displayed_Login_Successfully() throws Throwable {
	// Print a Log In message to the screen

    System.out.println("Login Successfully");
    
}

@When("^User LogOut from the Application$")
public void user_LogOut_from_the_Application() throws Throwable {
	// Find the element that's ID attribute is 'account_logout' (Log Out)
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
    		
}

@Then("^Message displayed LogOut Successfully$")
public void message_displayed_LogOut_Successfully() throws Throwable {
	// Print a Log In message to the screen

    System.out.println("LogOut Successfully");

    // Close the driver

    driver.quit();
    
}

@When("^User enters BadCredentials to LogIn$")
public void user_enters_BadCredentials_to_LogIn(DataTable usercredentials) throws Throwable {
	//Data Table reference
	List<List<String>> data = usercredentials.raw();
			
	//First set of data at 0,0 and 0,1
	driver.findElement(By.id("log")).sendKeys(data.get(0).get(0));
	driver.findElement(By.id("pwd")).sendKeys(data.get(0).get(1));
	
	driver.findElement(By.id("login")).click();
	System.out.println("Attempted bad login");
}

@Then("^Message displayed Login Unsuccessful$")
public void message_displayed_Login_Unsuccessful() throws Throwable {
	//Wait and verify the error message.
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	String actualString = driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]/strong")).getText(); 
	AssertJUnit.assertTrue(actualString.contains("ERROR"));
	boolean isTheTextPresent = driver.getPageSource().contains(": Invalid username.");
	assertTrue(isTheTextPresent);
}

@Then("^Forgot Password Link Appears$")
public void forgot_Password_Link_Appears() throws Throwable {
	//Verify the Forgot Password Link
	driver.findElement (By.xpath("//*[@id=\"ajax_loginform\"]/p[1]/a")).click();
	boolean isTheTextPresent = driver.getPageSource().contains("Please enter your username or email address. You will receive a link to create a new password via email.");
	assertTrue(isTheTextPresent);
	driver.quit();
}

@When("^User enters BadCredentials to LogIn II$")
public void user_enters_BadCredentials_to_LogIn_II(DataTable usercredentials) throws Throwable {
	
	List<Map<String,String>> data = usercredentials.asMaps(String.class,String.class);
	driver.findElement(By.id("log")).sendKeys(data.get(0).get("username")); 
    driver.findElement(By.id("pwd")).sendKeys(data.get(0).get("password"));
	driver.findElement(By.id("login")).click();
	System.out.println("Attempted bad login");
	
}

@When("^User enters BadCredentials to LogIn III$")
public void user_enters_BadCredentials_to_LogIn_III(List<Credentials>  usercredentials) throws Throwable {

	//Write the code to handle Data Table
	for (Credentials credentials : usercredentials) {			
		driver.findElement(By.id("log")).sendKeys(credentials.getUsername()); 
	    driver.findElement(By.id("pwd")).sendKeys(credentials.getPassword());
	    driver.findElement(By.id("login")).click();
	    System.out.println("Attempted");
	    driver.findElement(By.id("log")).clear();
	    driver.findElement(By.id("pwd")).clear();
	    System.out.println("Cleared");
	}
}

public class Credentials {
	private String username;
	private String password;

	public String getUsername() {
        return username;
    }
	public String getPassword() {
        return password;
    }	
}
	
}
