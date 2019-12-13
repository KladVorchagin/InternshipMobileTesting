import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ApiDemosPage {

    private AndroidDriver<MobileElement> driver;

    public ApiDemosPage() {
    }

    public ApiDemosPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expandable Lists']")
    private MobileElement expandListsTextView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1. Custom Adapter']")
    private MobileElement customAdapterTextView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
    private MobileElement namesList;

    public CustomNamesPage gotoNames(){
        expandListsTextView.click();
        customAdapterTextView.click();
        return new CustomNamesPage(driver);
    }

}
