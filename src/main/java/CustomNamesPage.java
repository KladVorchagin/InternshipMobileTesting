import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CustomNamesPage {

    private AndroidDriver<MobileElement> driver;

    public CustomNamesPage() {
    }

    public CustomNamesPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //todo generate page elements and methods
}
