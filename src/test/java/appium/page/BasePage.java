package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public static AndroidDriver driver;

    public WebElement findElement(By by){
        try {
           return driver.findElement(by);
        }catch (Exception e){
           handleAlert();
           return driver.findElement(by);
        }
    }
    public static void click(By by) {
        //todo: 递归是更好的

        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlert();

            driver.findElement(by).click();
        }
    }
    private static void handleAlert(){
        List<By> alertBoxs = new ArrayList<By>();
        //todo: 不需要所有的都判断是否存在
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        By tips = By.id("");
         Dimension size = driver.manage().window().getSize();
//        alertBoxs.add(By.xpath("dddd"));
        for (By alert:alertBoxs){
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if(alert.equals(tips)){

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
