import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {
    @BeforeTest
    public static AndroidDriver<AndroidElement> capabilitiesBuilder() throws MalformedURLException {
        File appDir = new File("src/main/resources"); //Add your apk file to this directory
        File app = new File(appDir, "General-Store.apk"); //Replace the name of your apk here!

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }
}
