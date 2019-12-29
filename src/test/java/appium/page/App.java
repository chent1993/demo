package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class App extends BasePage{
//todo:App单例
    public static void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "device");
        // desiredCapabilities.setCapability("appium", "F:\\xmind\\com.xueqiu.android_11.31_232.apk");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("autoGrantPermissions","true");
        desiredCapabilities.setCapability("noReset","true");//不重置
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println(driver.getCapabilities().getPlatform().toString().toLowerCase());
        System.out.println(driver.getCapabilities().getVersion());
       /* handleAlert();
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("home_search")));*/

       //速度会比较慢
        /*By adsLocator = By.id("xxx");
        List<WebElement> ads = driver.findElements(adsLocator);
        if (ads.size()>=1){
            ads.get(0).click();
        }*/

        //显示等待
        new WebDriverWait(driver,30).until(
               /* x->driver.getPageSource().contains("home_search")*/
                x->{
                    String xml = driver.getPageSource();
                    return xml.contains("home_search");
                }
        );

    }

    public static searchPage toSearch() {
       // driver.findElementById("home_search").click();

       /* By homeSearch = By.id("home_search");
        driver.findElement(homeSearch).click();*/
        BasePage basePage = new BasePage();
        basePage.parseSteps("/appium/page/App.yaml","toSearch");
        return new searchPage();
    }

    public static StockPage toStocks(){
        //click(By.xpath("//*[@resource-id='android:id/tabs']/android.widget.RelativeLayout[2]"));
       // click(By.xpath("//*[contains(@resource-id,'tab_name')and @text='自选']"));
        BasePage basePage = new BasePage();
        basePage.parseSteps("/appium/page/App.yaml","toStocks");
        return new StockPage();
    }


}
