package appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestDemo {
    public AndroidDriver driver;
    public void login() throws MalformedURLException {
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
    }
    @Test
    public void testRotate(){//横竖屏切换
        driver.rotate(ScreenOrientation.LANDSCAPE);//横屏
        driver.rotate(ScreenOrientation.PORTRAIT);//竖屏
        driver.navigate().back();//返回
    }
    @Test
    void testLogs(){//打印日志
        System.out.println(driver.manage().logs().getAvailableLogTypes());
    }

    @Test
    void  testPerformance(){//获取性能信息
        System.out.println(driver.getSupportedPerformanceDataTypes());
    }
}
