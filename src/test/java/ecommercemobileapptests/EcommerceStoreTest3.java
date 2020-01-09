package ecommercemobileapptests;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverfactory.AndroidDriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class EcommerceStoreTest3 extends AndroidDriverFactory {
	AndroidDriver<AndroidElement> androidDriver;
	TouchAction touchAction;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		System.out.println("Invoking Android Driver");

		try {
			//androidDriver = getAndroidDriverWithApp("REAL", "ANDROID DEVICE");
			androidDriver = getAndroidDriverWithApp("VIRTUAL", "SidduOreo");


		} catch (Exception e) {
			System.out.println("Exception occured while invoking android driver");
			System.out.println(e.getMessage());
		}

		if (androidDriver != null) {
			System.out.println("Android Driver Invoked");
		}

		touchAction = new TouchAction(androidDriver);
	}

	@Test
	public void addingSpecificProductToCart() throws InterruptedException {
		AndroidElement nameElement = androidDriver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
		nameElement.sendKeys("Anusha");

		AndroidElement femaleRadioElement = androidDriver.findElement(By.xpath("//*[@text='Female']"));
		femaleRadioElement.click();

		// Click on country dropdown
		AndroidElement countryDropdownElement = androidDriver.findElement(By.id("android:id/text1"));
		countryDropdownElement.click();

		// Scroll until Argentina is diaplayed
		androidDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		// Click on Argentina
		AndroidElement indiaElement = androidDriver.findElement(By.xpath("//*[@text='Argentina']"));
		indiaElement.click();

		// Click Lets shop button
		AndroidElement shopButtonElement = androidDriver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")");
		shopButtonElement.click();

		Thread.sleep(4000);

		// Only those ADD TO CART elements which are in view are returned
		List<AndroidElement> addToCartElements = androidDriver.findElements(By.xpath("//*[@text='ADD TO CART']"));

		// Adding all of then to cart
		for (AndroidElement addToCartElement : addToCartElements) {
			addToCartElement.click();
		}

		// Click on Cart present at top right
		AndroidElement cartElement = androidDriver
				.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
		cartElement.click();

		Thread.sleep(2000);

		// Tap on Email confirmation check box
		AndroidElement emailConfElement = androidDriver.findElement(By.className("android.widget.CheckBox"));
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(emailConfElement))).perform();

		// Long press terms and conditions to pen terms and conditions box
		AndroidElement termsElement = androidDriver
				.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(termsElement))
				.withDuration(Duration.ofSeconds(2))).perform();

		Thread.sleep(2000);
		// Close terms and conditions box
		AndroidElement closeTermsElement = androidDriver.findElement(By.id("android:id/button1"));
		closeTermsElement.click();
		Thread.sleep(2000);

		// Click complete purchase button
		AndroidElement completePurchaseElement = androidDriver
				.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
		completePurchaseElement.click();

		Thread.sleep(7000);
		// Now Web View is opened. Switch to web View

		Set<String> contextHandles = androidDriver.getContextHandles();
		for (String contextHandle : contextHandles) {
			System.out.println(contextHandle);
			if (contextHandle.equalsIgnoreCase("NATIVE_APP")) {
				// Do Nothing
			} else {
				androidDriver.context(contextHandle);
				System.out.println("Switched to Web View");
			}
		}
		
		androidDriver.findElement(By.name("q")).sendKeys("hello");

		androidDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));

		androidDriver.context("NATIVE_APP");

	}
}
