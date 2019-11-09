package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{

    public App loginWithCookie(){
        System.setProperty("webdriver.chrome.driver","D:\\drivers\\chrome\\77\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //当html下载完成之后，不等待解析完成，selenium会直接返回:预防人员列表加载时间过长
        options.setCapability("pageLoadStrategy","none");
        driver = new ChromeDriver(options);
        String url="https://work.weixin.qq.com/";
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        findElement(By.linkText("企业登录")).click();
        driver.manage().addCookie(new Cookie("wwrtx.sid","8Wyvf7O-Vo5BZXx8PfNNNBSqZtligvbfWGsityKwzI7fHgqhLOSvZe-aNRM47PaK"));
        driver.manage().addCookie(new Cookie("wwrtx.refid","1903228024922487"));
        driver.navigate().refresh();
        return this;
    }
    public ContactPage toContact(){
        findElement(By.linkText("通讯录")).click();
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        //findElement(By.linkText("通讯录")).click();
        //driver.findElement(By.linkText("添加成员")).click();
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public BroadcastPage toBroadcast(){
        findElement(By.linkText("管理工具"),5).click();
        findElement(By.partialLinkText("可通过公告等应用给成员群发消息"),8).click();
        return new BroadcastPage();
    }

}
