package ecommercemobileapptests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverfactory.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EcommerceStoreTest1 extends AndroidDriverFactory {

	AndroidDriver<AndroidElement> androidDriver;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		System.out.println("Invoking Android Driver");

		try {
			androidDriver = getAndroidDriverWithApp("REAL", "ANDROID DEVICE");

		} catch (Exception e) {
			System.out.println("Exception occured while invoking android driver");
			System.out.println(e.getMessage());
		}

		if (androidDriver != null) {
			System.out.println("Android Driver Invoked");
		}
	}

	@Test(enabled=false)
	public void firstTest() {
		AndroidElement nameElement = androidDriver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
		nameElement.sendKeys("Anusha");
		
		AndroidElement femaleRadioElement = androidDriver.findElement(By.xpath("//*[@text='Female']"));
		femaleRadioElement.click();
		
		// Click on country dropdown
		AndroidElement countryDropdownElement = androidDriver.findElement(By.id("android:id/text1"));
		countryDropdownElement.click();
		
		//Scroll until India is diaplayed
		androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		
		//Click on India
		AndroidElement indiaElement = androidDriver.findElement(By.xpath("//*[@text='Argentina']"));
		indiaElement.click();
		
		//AndroidElement shopButtonElement = androidDriver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop]"));
		AndroidElement shopButtonElement = androidDriver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")");

		shopButtonElement.click();
	}
	
	@Test
	public void toastMessageTest() {

		
		// Toast messages can not be located using UIAutomationViewer.
		//android.widget.Toast is the class name for toast messages. So we can directly use that classname
		
	
		
		AndroidElement femaleRadioElement = androidDriver.findElement(By.xpath("//*[@text='Female']"));
		femaleRadioElement.click();
		
		// Click on country dropdown
		AndroidElement countryDropdownElement = androidDriver.findElement(By.id("android:id/text1"));
		countryDropdownElement.click();
		
		//Scroll until India is diaplayed
		androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		
		//Click on India
		AndroidElement indiaElement = androidDriver.findElement(By.xpath("//*[@text='Argentina']"));
		indiaElement.click();
		
		//AndroidElement shopButtonElement = androidDriver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop]"));
		AndroidElement shopButtonElement = androidDriver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")");

		shopButtonElement.click();
		
		AndroidElement toastMessageElement = androidDriver.findElement(By.xpath("//android.widget.Toast[1]"));
		String toastMessage=toastMessageElement.getAttribute("name");

		System.out.println(toastMessage);
	}

}
