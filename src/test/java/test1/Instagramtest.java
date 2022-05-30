package test1;

import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Instagramtest {
	static WebElement email;
	static WebElement name;
	static WebElement username;
	static WebElement passsword;
	static WebElement b1;
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
//creating driver instance
		driver = new ChromeDriver();
		int number = 0;
		File s = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File("tutorialpoint" + number + ".png"));
		number++;
		driver.get("https://www.instagram.com/");
//adding timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();

//Getting the name ,password ,login button elements
		WebElement name = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(with(By.tagName("button")).below(password));

//Sending Values for username and password
		name.sendKeys("v6716543@gmail.com");
//Thread.sleep(5000);
		password.sendKeys("Qwerty@123");
//click on the login button
		loginButton.click();
		Thread.sleep(4000);

		WebElement popUp1 = driver.findElement(By.className("yWX7d"));
//close the first popup
		popUp1.click();
		Thread.sleep(4000);

		WebElement popUp2 = driver.findElement(By.xpath("//*[contains(text(),'Not Now')]"));
//close the second popup
		popUp2.click();
		WebElement searchBar = driver.findElement(By.className("d_djL"));
//search for an account(Evil Tester) in the search bar
		searchBar.sendKeys("EvilTester");
		Thread.sleep(5000);
		WebElement listElement = driver.findElement(
				By.xpath("//a[@href='/eviltester/']//div//div//div//div//div//div[contains(text(),'eviltester')]"));
		listElement.click();
		Thread.sleep(5000);
		WebElement followBtn = driver.findElement(By.xpath("//*[contains(text(),'Follow')]"));
//Follow the account searched if it is not already followed
		if (followBtn.isDisplayed()) {
			followBtn.click();
		}

//click on Insta Icon to go to home page
		WebElement InstaIcon = driver.findElement(By.className("s4Iyt"));
		InstaIcon.click();

//click on explore Button
		WebElement exploreBtn = driver.findElement(By.xpath("//a[@href='/explore/']//*[name()='svg']"));
		exploreBtn.click();

		Thread.sleep(5000);
//opening a post and viewing it
		WebElement post = driver.findElement(By.className("_9AhH0"));
		post.click();
		Thread.sleep(5000);
//close the post
		WebElement closeBtn = driver
				.findElement(By.xpath("//div[@class='NOTWr']//div[@class='QBdPU ']//*[name()='svg']"));
		closeBtn.click();
		Thread.sleep(5000);

//Click on the profile Icon
		WebElement profile = driver.findElement(By.xpath("//div[@class='EforU']"));
		profile.click();

//click on about profile section
		WebElement aboutBtn = driver.findElement(By.xpath("//div[contains(text(),'Profile')]"));
		aboutBtn.click();
		Thread.sleep(5000);

//click on followers button
		WebElement checkfollowersBtn = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/header[1]/section[1]/ul[1]/li[3]/a[1]/div[1]"));
		checkfollowersBtn.click();
		Thread.sleep(5000);

//close the popup
		WebElement closeIcon = driver.findElement(
				By.xpath("//div[contains(@class,'WaOAr _8E02J')]//div[contains(@class,'QBdPU')]//*[name()='svg']"));
		closeIcon.click();
//Thread.sleep(5000);

//click on profile Icon
		WebElement profileBtn = driver.findElement(
				By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[6]/div[1]/span/img"));
		profileBtn.click();

//logout
		WebElement logoutBtn = driver.findElement(By.xpath("//div[contains(text(),'Log Out')]"));
		logoutBtn.click();

	}

}