package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StockPage extends BasePage{

    public StockPage deleteAll(){
        click(By.id("com.xueqiu.android:id/edit_group"));
        click(By.id("com.xueqiu.android:id/check_all"));
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        click(By.id("com.xueqiu.android:id/md_buttonDefaultPositive"));
        click(By.id("com.xueqiu.android:id/action_close"));
        return this;
    }

    public List<WebElement> getAllStocks(){
        List<WebElement> stocks = driver.findElements(By.id("com.xueqiu.android:id/portfolio_stockName"));
        for (WebElement element:stocks){
            element.getText();
        }
        return stocks;
    }

    public  void  addDefultStocks(){
        click(By.id("com.xueqiu.android:id/add_to_portfolio_stock"));
       /* By by = By.xpath("//*[text()='加入自选股']");
        click(by);*/
    }
}
