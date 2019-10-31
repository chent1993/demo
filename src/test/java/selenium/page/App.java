package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{

    public App loginWithCookie(){
        System.setProperty("webdriver.chrome.driver","D:\\drivers\\chrome\\77\\chromedriver.exe");
        driver = new ChromeDriver();
        String url="https://work.weixin.qq.com/";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        findElement(By.linkText("企业登录")).click();
        driver.manage().addCookie(new Cookie("wwrtx.sid","8Wyvf7O-Vo5BZXx8PfNNNEDDKMzh4ffRuXThOqbAuceE8qiH4WUOEpTT9pLqviXP"));
        driver.manage().addCookie(new Cookie("wwrtx.refid","19032280241388471"));
        driver.navigate().refresh();
        return this;
    }
    public ContactPage toContact(){
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        driver.findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

}
