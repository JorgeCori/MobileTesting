import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppiumDemo extends Base{
    @Test
    public void testIdentify() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilitiesBuilder();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //There are 4 kinds of ways you can locate an element (like in Selenium)
        // There's XPath, id, class name and through the AndroidUIAutomator

        driver.findElementByXPath("//android.widget.TextView[@text = 'Preference']").click();
        driver.findElementByXPath("//android.widget.TextView[@text = '3. Preference dependencies']").click();

        driver.findElementById("android:id/checkbox").click();
        //using the following notation ("XPathToElement)[index]") you can get a specific element if there's more than one
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();

        driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
        driver.findElementsByClassName("android.widget.Button").get(1).click();

        //driver.findElementByAndroidUIAutomator("text(\"Views\")");
    }
    @Test
    public void longPressDemo() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilitiesBuilder();
        //actions in Appium, just like in Selenium, express things you do aside from clicking and sending Keys
        //This demo uses actions to tap and longPress/swipe an element
        TouchAction touchAction = new TouchAction(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Clicking an element
        driver.findElementByXPath("//android.widget.TexVIew[@test = 'Views']").click();

        //Tapping an element
        WebElement dateWidgetsOption = driver.findElementByXPath("//android.widget.TextView[@text = 'Date Widgets']");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(dateWidgetsOption))).perform();
        //Quite verbose, right? there are two ways of shortening this: creating a utilities method or making static imports
        // import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
        // import static io.appium.java_client.touch.TapOptions.tapOptions;
        // import static io.appium.java_client.touch.offset.ElementOption.element;
        // import static java.time.Duration.ofSeconds;

        //By doing so, you can rewrite the previous line to
        //touchAction.tap(tapOptions().withElement(element(dateWidgetsOption))).perform();
        //shorter, but you're better off using a utilities class.

        //Tapping another element
        WebElement inLineOption = driver.findElementByXPath("//android.widget.TextView[@text = '2. Inline']");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(inLineOption))).perform();

        //Clicking on an element
        driver.findElementByXPath("//[@content-desc = '3']").click();

        //Let's swipe!
        //You swipe by long pressing a WebElement and moving it to another place!
        //You can swipe to a certain position or to another element.
        //This same principle is used for dragging and dropping!

        WebElement minuteOptionStarter = driver.findElementByXPath("//*[@content-desc = '15']");
        WebElement minuteOptionFinal = driver.findElementByXPath("//*[@content-desc = '45']");
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(minuteOptionStarter)).withDuration(Duration.ofSeconds(2))).moveTo(ElementOption.element(minuteOptionFinal)).release().perform();
        //yikes
    }

    @Test
    public void scrollDemo() throws MalformedURLException {
        //Let's scroll.
        //Sometimes you want to scroll until you see a certain thing.
        //This is what this is for
        AndroidDriver<AndroidElement> driver = capabilitiesBuilder();
        TouchAction touchAction = new TouchAction(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElementByXPath("//android.widget.TextView[@text = 'Views']").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))");
        //yikes
        //using AndroidUIAutomator allows you to do WAY more things. It's like JavaScript, but for phones :D
    }

    @Test
    public void shoppingTest() throws MalformedURLException{
        //Let's put everything together
        //there will be some new stuff in here, but I'll explain along the way
        AndroidDriver<AndroidElement> driver = capabilitiesBuilder();
        TouchAction touchAction = new TouchAction(driver);

        //We tap in a dropdown selector element
        WebElement countrySelector = driver.findElementByClassName("android.widget.Spinner");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(countrySelector))).perform();
        //We scroll in the dropdown menu until we see an option with a certain text
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))");
        driver.findElementByXPath("//android.widget.TextView[@text = 'Aruba']").click();
        //We hide the keyboard
        //There are two ways of doing it: by using the method that hides the keyboard or tu use the BACK navigation button
        driver.navigate().back();
        //driver.hideKeyboard();

        //We click in an input element and enter a name
        WebElement nameField = driver.findElementByClassName("android.widget.EditText");
        nameField.click();
        nameField.sendKeys("Original Name");
        driver.hideKeyboard();

        //Clicking on a radio button
        driver.findElementByXPath("//android.widget.RadioButton[qtext = 'Female']").click();

        //clicking the submit button
        driver.findElementByClassName("android.widget.Button").click();

        //Add first two visible items to cart by clicking the button Add to cart
        List<AndroidElement> items = driver.findElementsByXPath("//android.widget.TextView[@text = 'ADD TO CART']");
        for(AndroidElement item : items){
            touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(item))).perform();
        }

        //Clicking on ShoppingPage Icon
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"))))).perform();

        //Clicking on Purchase Button
        WebElement purchaseButton = driver.findElementById("com.androidsample.generalstore:id/brnProceed");
        purchaseButton.click();

        //NEW STUFF
        //When clicking on the purchase button,it takes you to a browser.
        //First off, you can't interact with it because you have to switch context
        //Then you use ole' plain Selenium to interact with stuff

        //getting the handles
        Set<String> contexts = driver.getContextHandles();
        for(String context:contexts){
            //check which context is the one you're looking for
            System.out.println(context);
        }

        //switch to the context
        driver.context("WEBVIEW_com.androidsample.generalstore");

        //Automate with Selenium
        driver.findElement(By.xpath("//*[name = 'q']")).sendKeys("You did it!");

        //To run this test you'll need to use the appium server through the command line and not desktop
        //Check the WebBrowser in Appium section in the README.md in the repo
    }
}
