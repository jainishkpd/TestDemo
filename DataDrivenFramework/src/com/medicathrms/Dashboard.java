package com.medicathrms;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testdata.ScreenCapture;
import com.testdata.TestData;
import com.setup.BrowserSetup;

public class Dashboard extends BrowserSetup {
	
	@Test
	public void dashboard() throws IOException
	{ 
		String page_title = driver.getTitle();
		System.out.println(page_title);
		
		String expected_page_url = driver.getCurrentUrl();
		System.out.println(expected_page_url);
		Assert.assertEquals(expected_page_url, TestData.dashboard_actual_page_url);
		
		System.out.println("Dashboard Page.");
		ScreenCapture.passScreenCapture();
	}

}
