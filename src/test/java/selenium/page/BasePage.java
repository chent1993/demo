package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public static WebDriver driver;
    public WebElement findElement(By by){

        return driver.findElement(by);
    }
    public WebElement findElement(By by,int timeout){
        if (timeout>0)
            waitClickable(by,timeout);
        return driver.findElement(by);
    }
    public void waitClickable(By by,int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void quit(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    public void demo(){
        WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('.cheese')[0]");

        Select select = new Select(driver.findElement(By.tagName("select")));
        select.deselectAll();
        select.selectByVisibleText("Edam");

    }
}
