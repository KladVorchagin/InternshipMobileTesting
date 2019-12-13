import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class BaseTest {
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AndroidDriver<MobileElement> driver;

    /*
    #1
    Functions that have the @BeforeSuite annotation will be run before every test suite run.
    Such a function can be used to setup the application into the right state/condition before the individual test cases are run.
    */
    @BeforeSuite
    public void setupAppium() throws MalformedURLException {
        /*
        #2
        We set the URL to the localhost where we will be running appium
        */
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);

        /*
        #3
        Setup an object that will hold the Appium Capabilities
        */
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/vkorchagin/Downloads/InternshipMobileTesting/ApiDemos.apk");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        /*
        #4
        Initialise the driver with the URL and Capabilities object.
        This driver will be the main object used to interact with the device
        */
        driver = new AndroidDriver<>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.resetApp();
    }

    /*
    #5
    Functions that have the @AfterSuite annotation will be run after every test suite run.
    These functions should be used to clean up any changes done on the device before the next test suite run.
    */
    @AfterSuite
    public void uninstallApp() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

    /*
    #6
    Functions that start with @Test will be identified as TestNG tests.
    You can enable or disable a test in the test suite by toggling the value in (enabled=true).
    */
    @Test(enabled = true)
    public void myFirstTest() {
        driver.resetApp();
    }

    @Test(enabled = true)
    public void gesturesLongTapTest() {
        MobileElement viewsTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Views']"));
        viewsTextView.click();

        TouchAction touchAction = new TouchAction(driver);
        MobileElement expListsTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Expandable Lists']"));
        touchAction.tap(tapOptions().withElement(element(expListsTextView))).perform();

        MobileElement customAdapterTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='1. Custom Adapter']"));
        customAdapterTextView.click();

        MobileElement pn = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='People Names']"));
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(element(pn)).withDuration(Duration.ofSeconds(2))).release().perform();
        Assert.assertTrue(driver.findElement(MobileBy.id("android:id/title")).isDisplayed(), "Sample menu is not displayed");
    }

    @Test(enabled = true)
    public void gesturesLongTapTestv2() {
        CustomNamesPage customNamesPage = new HomePage(driver)
                .goToViews()
                .gotoNames();
        customNamesPage.getSampleMenuByLongTap();
        Assert.assertTrue(customNamesPage.sampleMenuTitle.isDisplayed(), "Sample menu is not displayed");
    }

    @Test(enabled = true)
    public void gesturesSwipeTest() {
        MobileElement viewsTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Views']"));
        viewsTextView.click();

        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Date Widgets']")).click();
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='2. Inline']")).click();

        //Elements for verification
        MobileElement expectedHours = driver.findElement(MobileBy.id("android:id/hours"));
        MobileElement expectedMinutes = driver.findElement(MobileBy.id("android:id/minutes"));
        MobileElement dateSeparator = driver.findElement(MobileBy.id("android:id/separator"));
        //

        Assert.assertEquals(String.format("%s%s%s", expectedHours.getText(), dateSeparator.getText(), expectedMinutes.getText()), "12:15", "Actual date is not correct");

        driver.findElement(MobileBy.xpath("//*[@content-desc='9']")).click();

        TouchAction touchAction = new TouchAction(driver);

        MobileElement first = driver.findElement(MobileBy.xpath("//*[@content-desc='15']"));
        MobileElement second = driver.findElement(MobileBy.xpath("//*[@content-desc='45']"));
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(element(first)).withDuration(Duration.ofSeconds(2))).moveTo(element(second)).release().perform();
        Assert.assertEquals(String.format("%s%s%s", expectedHours.getText(), dateSeparator.getText(), expectedMinutes.getText()), "9:45", "Actual date is not correct");
    }

    @Test(enabled = true)
    public void gesturesScrollTest() {
        MobileElement viewsTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Views']"));
        viewsTextView.click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Radio Group\"))");
        Assert.assertTrue(driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Radio Group']")).isDisplayed(), "Sample menu is not displayed");
    }

    @Test(enabled = true)
    public void gesturesDragDropTest() {
        MobileElement viewsTextView = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Views']"));
        viewsTextView.click();
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
        MobileElement source = driver.findElementsByClassName("android.view.View").get(0);
        MobileElement destination = driver.findElementsByClassName("android.view.View").get(1);

        TouchAction touchAction = new TouchAction(driver);
        //longpress(source)/move(destination)/release
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
    }

    @Test(enabled = false)
    public void homeWorkTest1() {
        //Views -> Tabs -> Scrollable
        //Verify content text in Tab 27 (open the page, scroll to Tab 27, verify text in tab)
    }
    @Test(enabled = false)
    public void homeWorkTest2() {
        //Views -> Visibility
        //create and automate a test case for checking buttons "INVIS" (hides element B) and "VIS" (makes element B visible again)
    }

    @Test(enabled = false)
    public void homeCheeseTest() {
        //Views -> Search View -> Filter
        //Verify that there are exactly 4 ricotta cheeses on the cheese list
    }
}
