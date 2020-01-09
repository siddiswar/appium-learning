package ecommercemobileapptests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverfactory.AndroidDriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EcommerceStoreTest2 extends AndroidDriverFactory {
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

	@Test
	public void addingSpecificProductToCart() {
		AndroidElement nameElement = androidDriver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
		nameElement.sendKeys("Anusha");

		AndroidElement femaleRadioElement = androidDriver.findElement(By.xpath("//*[@text='Female']"));
		femaleRadioElement.click();

		// Click on country dropdown
		AndroidElement countryDropdownElement = androidDriver.findElement(By.id("android:id/text1"));
		countryDropdownElement.click();

		// Scroll until India is diaplayed
		androidDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		// Click on Argentina
		AndroidElement indiaElement = androidDriver.findElement(By.xpath("//*[@text='Argentina']"));
		indiaElement.click();

		// Click Lets shop button
		AndroidElement shopButtonElement = androidDriver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")");

		shopButtonElement.click();

		// Scroll until Jordan 6 Rings
		// To scroll to it, first locate the whole container and then scroll to the
		// product within that container
		androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(text(\"Jordan 6 Rings\"))"));
		
		//androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));

		int count = androidDriver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for (int i = 0; i < count; i++) {
			String text = androidDriver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();

			if (text.equalsIgnoreCase("Jordan 6 Rings"))

			{
				androidDriver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}

		androidDriver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		String lastpageText = androidDriver.findElement(By.id("com.androidsample.generalstore:id/productName"))
				.getText();

		System.out.println(lastpageText);

		// androidDriver.assertEquals("Jordan 6 Rings", lastpageText);

	}
}
