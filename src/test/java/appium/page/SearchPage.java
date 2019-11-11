package appium.page;

import org.openqa.selenium.By;

public class SearchPage extends BasePage{
    private By inputBox=By.id("search_input_text");
    public SearchPage search(String keyword) {
        //driver.findElementById("search_input_text").sendKeys(keyword);
        driver.findElement(inputBox).sendKeys(keyword);
        click(By.id("com.xueqiu.android:id/name"));
        return this;
    }

    public Float getCurrentPrice() {
        return Float.valueOf(findElement(By.id("com.xueqiu.android:id/current_price")).getText());
    }

    public App cancel(){
        click(By.id("com.xueqiu.android:id/action_close"));
        return new App();
    }
}
