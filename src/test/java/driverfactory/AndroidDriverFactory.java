package driverfactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidDriverFactory {

	public static AndroidDriver<AndroidElement> getAndroidDriverWithApp(String mobileEnvironment, String deviceName)
			throws MalformedURLException {
		//mobileEnvironment = REAL / VIRTUAL
		//deviceName = 'Android Device' for Real Device / AVDName for Emulator
		
		System.out.println("mobileEnvironment :" + mobileEnvironment);
		System.out.println("deviceName :" + deviceName);
		
		File appDir = new File("apps");
		// File app = new File(appDir, "ApiDemos-debug.apk");
		File app = new File(appDir, "General-Store.apk");
		System.out.println(app.getAbsolutePath());

		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (mobileEnvironment.equalsIgnoreCase("REAL")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		} else if (mobileEnvironment.equalsIgnoreCase("VIRTUAL")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		}

		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		URL androidServerURL = new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<AndroidElement> androidDriver = new AndroidDriver<AndroidElement>(androidServerURL, capabilities);
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return androidDriver;
	}
	
	public static AndroidDriver<AndroidElement> getAndroidDriverWithChromeBrowser(String mobileEnvironment, String deviceName)
			throws MalformedURLException {
		
		System.out.println("mobileEnvironment :" + mobileEnvironment);
		System.out.println("deviceName :" + deviceName);

		// Here no app is needed. Just pass BROWSER_NAME compatibility
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (mobileEnvironment.equalsIgnoreCase("REAL")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		} else if (mobileEnvironment.equalsIgnoreCase("VIRTUAL")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		}
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "CHROME");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		URL androidServerURL = new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<AndroidElement> androidDriver = new AndroidDriver<AndroidElement>(androidServerURL, capabilities);
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return androidDriver;
	}
}
