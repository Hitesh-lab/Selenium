package test1;

import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

//import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SeleniumTest {
	static WebElement email;
	static WebElement name;
	static WebElement username;
	static WebElement passsword;
	static WebElement signupButton;
	static WebDriver driver;
	static int number = 0;
	static void testcase(String pemail, String pname, String pusername, String ppassword, String pexcpected)
			throws InterruptedException, IOException {
		//Getting all the web elements
		name = driver.findElement(By.name("fullName"));
		email = driver.findElement(with(By.tagName("input")).above(name));
		username = driver.findElement(By.name("username"));
		passsword = driver.findElement(By.name("password"));
		signupButton = driver.findElement(with(By.tagName("button")).below(passsword));
		Thread.sleep(3000);
		//Sending all the values 
		name.sendKeys(pname);
		email.sendKeys(pemail);
		username.sendKeys(pusername);
		passsword.sendKeys(ppassword);
		Thread.sleep(4000);
		//Checking if sign up button is enabled
		if (signupButton.isEnabled()) {
			signupButton.click();
			File s = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(s, new File("Screenshot"+number+".png"));
			number++;
		
			if (pexcpected != "") {
				Thread.sleep(4000);
				WebElement msg = driver.findElement(By.id("ssfErrorAlert"));
				Assert.assertEquals(pexcpected, msg.getText());
				System.out.println("Success/Pass");
			} else {
				WebElement nextPage = driver.findElement(By.xpath("//div[contains(text(),'Add Your Birthday')]"));
				String validtext = nextPage.getText();
				Assert.assertEquals("Add Your Birthday", validtext);
			}

		} else {
			System.out.println("Success/Pass");
		}

	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.instagram.com/accounts/emailsignup/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		name = driver.findElement(By.name("fullName"));
		email = driver.findElement(with(By.tagName("input")).above(name));
		username = driver.findElement(By.name("username"));
		passsword = driver.findElement(By.name("password"));
		signupButton = driver.findElement(with(By.tagName("button")).below(passsword));

		Thread.sleep(1000);
// Test case 1(min-1)
		testcase("test@gmail.com", "bh", "", "test123@", "test");
		driver.navigate().refresh();

// Test case 2(min)
		testcase("test1@gmail.com", "bh", "t", "test123@", "A user with that username already exists.");
		driver.navigate().refresh();

// Test case 3(min+1)
		testcase("test2@gmail.com", "bh", "te", "test123@", "This username isn't available. Please try another.");
		driver.navigate().refresh();

/// Test case 4(nom)
		testcase("team4bugsquasherssel@gmail.com", "bh", "test_12dt234y", "test123@", "");
		driver.get("https://www.instagram.com/accounts/emailsignup/");

/// Test case 5(nom)
		testcase("test4@gmail.com", "bh", "1234", "test123@", "Your username cannot contain only numbers.");
		driver.navigate().refresh();

/// Test case 6(max-1)
		testcase("tdemo1@gmail.com", "bh", "test_12dtyu_16373882yeyebs67_", "test123@", "");
		driver.get("https://www.instagram.com/accounts/emailsignup/");

/// Test case 7(max)
		testcase("test404t@gmail.com", "bh", "test_12dtyu_16373882yeyebs67s_", "test123@", "");
		driver.get("https://www.instagram.com/accounts/emailsignup/");

/// Test case 8(max+1)
// username will be truncated back to max so therefore testcase is valid.
		testcase("v35f253@gmail.com", "bh", "test_12dtyu_16373882yeyebs67s_stt", "test123@", "");
		driver.get("https://www.instagram.com/accounts/emailsignup/");

		driver.quit();
	}

}