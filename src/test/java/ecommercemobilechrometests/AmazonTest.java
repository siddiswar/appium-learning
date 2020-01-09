package ecommercemobilechrometests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverfactory.AndroidDriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AmazonTest extends AndroidDriverFactory {
	AndroidDriver<AndroidElement> androidDriver;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		System.out.println("Invoking Android Driver");

		try {
			//androidDriver = getAndroidDriverWithChromeBrowser("REAL", "ANDROID DEVICE");
			androidDriver = getAndroidDriverWithChromeBrowser("VIRTUAL", "SidduOreo");


		} catch (Exception e) {
			System.out.println("Exception occured while invoking android driver");
			System.out.println(e.getMessage());
		}

		if (androidDriver != null) {
			System.out.println("Android Driver Invoked");
		}
	}

	@Test
	public void searchOnAmazonTest() {
		
		System.out.println(androidDriver.getContext());
		androidDriver.get("https://www.amazon.co.uk/");
		System.out.println(androidDriver.getTitle());
		androidDriver.findElement(By.xpath("//*[@name='k']")).sendKeys("shoes");
		androidDriver.findElement(By.xpath("//*[@value='Go']")).click();
		
	}
}
