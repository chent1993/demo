package appium.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static AndroidDriver driver;

    public WebElement findElement(By by){
        System.out.println(by);
        try {
           return driver.findElement(by);
        }catch (Exception e){
           handleAlert();
           return driver.findElement(by);
        }
    }
    public static void click(By by) {
        //todo: 递归是更好的
        System.out.println(by);
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlert();

            driver.findElement(by).click();
        }
    }

    public List<WebElement> findElements(By by){
        System.out.println(by);
        try {
            return driver.findElements(by);
        } catch (Exception e) {
           // handleAlert();
            return driver.findElements(by);
        }
    }
    public static void handleAlert(){
        List<By> alertBoxs = new ArrayList<By>();
        //todo: 不需要所有的都判断是否存在
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        By tips = By.id("com.xueqiu.android:id/snb_tip_text");
        alertBoxs.add(tips);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        for (By alert:alertBoxs){
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if(alert.equals(tips)){
                Dimension size = driver.manage().window().getSize();
               try {
                   //某些元素是动态变化的，等点击元素时查询到的元素可能已经变化
                   //需要在点击之前再判断一次
                   if (driver.findElements(tips).size()>=1){
                       new TouchAction(driver).tap(PointOption.point(size.width/2,size.height/2)).perform();
                   }
               }catch (Exception e){
                    e.printStackTrace();
               }
            }else  if (ads.size() >= 1) {
                ads.get(0).click();
            }
        }
       /* alertBoxs.forEach(alert -> {
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size() >= 1) {
                ads.get(0).click();
            }
        });*/
    }
    private static void handleAlertByPageSource(){
        //todo: xpath匹配， 标记 定位
        String xml=driver.getPageSource();
        List<String> alertBoxs=new ArrayList<String>();
        alertBoxs.add("xxx");
        alertBoxs.add("yyy");
        for (String alert:alertBoxs){
            if(xml.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        }
        /*alertBoxs.forEach(alert -> {
            if(xml.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        })*/;

    }
}
