import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class CustomNamesPage {

    private AndroidDriver<MobileElement> driver;

    public CustomNamesPage() {
    }

    public CustomNamesPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
    private MobileElement peopleNames;

    @AndroidFindBy(id = "android:id/title")
    protected MobileElement sampleMenuTitle;


    public CustomNamesPage getSampleMenuByLongTap(){
        new TouchAction(driver).longPress(LongPressOptions.longPressOptions().withElement(element(peopleNames)).withDuration(Duration.ofSeconds(2))).release().perform();
        return new CustomNamesPage(driver);
    }
}
