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
        capabilities.setCapability(MobileCapabilityType.APP, ""); //todo set APK path
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
    public void quit() {
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
        //todo
    }

    @Test(enabled = true)
    public void gesturesLongTapTestv2() {
        //todo
    }

    @Test(enabled = true)
    public void gesturesSwipeTest() {
        //todo
    }

    @Test(enabled = true)
    public void gesturesScrollTest() {
        //todo
    }

    @Test(enabled = true)
    public void gesturesDragDropTest() {
        //todo
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
