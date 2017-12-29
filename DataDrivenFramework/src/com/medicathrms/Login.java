package com.medicathrms;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testdata.ScreenCapture;
import com.setup.BrowserSetup;
import com.testdata.TestData;

public class Login extends BrowserSetup {
	
	@Parameters("browser")
	@Test
	public void passSetup(String mbrowser) throws InterruptedException
	{
		setup(mbrowser);
	}
	
	public void login(String validusername, String validpassword) throws InterruptedException
	{
		String title = driver.getTitle();
		System.out.println(title);
	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.get(TestData.url);
		driver.findElement(By.id("UserName")).clear();
		driver.findElement(By.id("UserName")).sendKeys(validusername);
		driver.findElement(By.id("Password")).sendKeys(validpassword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(5000);    
	}
	
	@Test(dependsOnMethods = {"passSetup"})
	public void loginMethod() throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(TestData.url);
		
		login(TestData.username1, TestData.password1);
		System.out.println("Username & Password both are invalid");
		
		login(TestData.username2, TestData.password2);
		System.out.println("Username = Correct & Password = Wrong");
		
		login(TestData.username3, TestData.password3);
		System.out.println("Username = Wrong & Password = Correct");
		
		login(TestData.validusername, TestData.validpassword);
		System.out.println("Login Successfully.");
		
		Thread.sleep(2500);
	}
	
	@Test(dependsOnMethods = {"loginMethod"})
	public void getOTPMethod() throws IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='ResultBox']"))));
		
		String getMessage = driver.findElement(By.xpath("//div[@id='ResultBox']")).getText();
		System.out.println(getMessage);
		ScreenCapture.passScreenCapture();
	}
	
	@Test(dependsOnMethods = {"getOTPMethod"})
	public void sendOTPPage() throws IOException
	{
		String expected_page_url = driver.getCurrentUrl();
		System.out.println(expected_page_url);
		
		Assert.assertEquals(expected_page_url, TestData.actual_page_url);
		
		String username = driver.findElement(By.id("UserName")).getAttribute("value");
		System.out.println(username);
		
		String new_otp = driver.findElement(By.id("NewOTP")).getAttribute("value");
		System.out.println(new_otp);
		driver.findElement(By.id("OTP")).sendKeys(new_otp);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Login Successfully..!!!!");
		ScreenCapture.passScreenCapture();
	}

}
