package apidemosmobileapptests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MobileGestureTests {

	AndroidDriver<AndroidElement> androidDriver;
	TouchAction touchAction;

	@BeforeMethod
	public void androidDriverSetUp() throws MalformedURLException {
		System.out.println("Before Method=============================================");
		File appDir = new File("apps");
		File app = new File(appDir, "ApiDemos-debug.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SidduPie");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		URL androidServerURL = new URL("http://127.0.0.1:4723/wd/hub");

		androidDriver = new AndroidDriver<AndroidElement>(androidServerURL, capabilities);
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		AndroidElement prefElement = androidDriver
				.findElement(By.xpath("//android.widget.TextView[@text='Preference']"));

		touchAction = new TouchAction(androidDriver);

	}

	@Test(enabled = true)
	public void tapTest() {
		AndroidElement prefElement = androidDriver
				.findElement(By.xpath("//android.widget.TextView[@text='Preference']"));
		
		// Tap on Element
		touchAction.tap(TapOptions.tapOptions()
										.withElement(ElementOption.element(prefElement)))
					.perform();
	}

	@Test(enabled = true)
	public void longPressTest() {
		AndroidElement viewElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		viewElement.click();

		AndroidElement expListElement = androidDriver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		expListElement.click();

		AndroidElement cusAdElement = androidDriver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")");

		cusAdElement.click();

		AndroidElement peopleNameElement = androidDriver.findElementByAndroidUIAutomator("text(\"People Names\")");
		System.out.println(peopleNameElement.getText());

		// Long Press Source Element With Duration + Move To Target Element + Release
		touchAction.longPress(LongPressOptions.longPressOptions()
												.withElement(ElementOption.element(peopleNameElement))
												.withDuration(Duration.ofSeconds(2)))
					.release()
					.perform();

	}

	@Test(enabled = true)
	public void swipeTest() {
		AndroidElement viewElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		viewElement.click();

		AndroidElement dateWidgetElement = androidDriver.findElementByAndroidUIAutomator("text(\"Date Widgets\")");
		dateWidgetElement.click();

		AndroidElement inlineElement = androidDriver.findElementByAndroidUIAutomator("text(\"2. Inline\")");

		inlineElement.click();

		AndroidElement fromElement = androidDriver.findElementByAndroidUIAutomator("description(\"12\")");
		AndroidElement toElement = androidDriver.findElementByAndroidUIAutomator("description(\"5\")");

		// Long Press Source Element With Duration + Move To Target Element + Release
		touchAction.longPress(LongPressOptions.longPressOptions()
												.withElement(ElementOption.element(fromElement))
												.withDuration(Duration.ofSeconds(2)))
				.moveTo(ElementOption.element(toElement))
				.release()
				.perform();

	}

	@Test(enabled = true)
	public void scrollTest() {
		AndroidElement viewElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		viewElement.click();
		//Scroll till the element 
		androidDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
	}

	@Test(enabled = true)
	public void dragAndDropTest() {
		AndroidElement viewElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		viewElement.click();

		AndroidElement dragAndDropElement = androidDriver
				.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']");
		dragAndDropElement.click();

		AndroidElement sourceElement = androidDriver.findElementsByClassName("android.view.View").get(0);
		AndroidElement targetElement = androidDriver.findElementsByClassName("android.view.View").get(1);

		
		//Long Press Source Element+ Move To Target Element + Release
		touchAction.longPress(LongPressOptions.longPressOptions()
												 .withElement(ElementOption.element(sourceElement)))
				.moveTo(ElementOption.element(targetElement))
				.release()
				.perform();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("After Method=============================================");

		androidDriver.quit();
		System.out.println("Done");
	}

}
