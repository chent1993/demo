package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{

    public StockPage deleteAll(){
       /* click(By.id("com.xueqiu.android:id/edit_group"));
        click(By.id("com.xueqiu.android:id/check_all"));
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        click(By.id("com.xueqiu.android:id/md_buttonDefaultPositive"));
        click(By.id("com.xueqiu.android:id/action_close"));*/
        BasePage basePage = new BasePage();
        basePage.parseSteps("/appium/page/StockPage.yaml","deleteAll");
        return this;
    }

    public List<String> getAllStocks(){
        //new WebDriverWait(driver,10);
        /*try {
            click(By.id("com.xueqiu.android:id/snb_tip_text"));
        }catch (Exception e){
        }*/
        List<String> stockList=new ArrayList<>();
        List<WebElement> stocks = findElements(By.id("com.xueqiu.android:id/portfolio_stockName"));
        for (WebElement element:stocks){
            stockList.add(element.getText());
        }
        return stockList;
    }

    public  StockPage  addDefultStocks(){
        click(By.id("com.xueqiu.android:id/add_to_portfolio_stock"));
       return this;
    }

    public searchPage toSearch(){
        click(By.id("com.xueqiu.android:id/action_search"));
        return new searchPage();
    }

}
