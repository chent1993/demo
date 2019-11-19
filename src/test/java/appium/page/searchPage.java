package appium.page;

import org.openqa.selenium.By;

import java.util.HashMap;

public class searchPage extends BasePage{
    private By inputBox=By.id("search_input_text");
    public searchPage search(String keyword) {
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("keyword",keyword);
        setParams(hashMap);
        parseSteps("/appium/page/searchPage.yaml","search");
        /*driver.findElement(inputBox).sendKeys(keyword);
        click(By.id("com.xueqiu.android:id/name"));*/
        return this;
    }

    public Float getCurrentPrice() {
        parseSteps("/appium/page/searchPage.yaml","getCurrentPrice");
        return Float.valueOf(getResults().get("price").toString());
        //return Float.valueOf(findElement(By.id("com.xueqiu.android:id/current_price")).getText());
    }

    public App cancel(){
        parseSteps();
        //click(By.id("com.xueqiu.android:id/action_close"));
        return new App();
    }

    public searchPage addSearch(){
        parseSteps("addSearch");
        /*click(By.id("com.xueqiu.android:id/follow_btn"));
        //有时不弹框
        if (findElements(By.id("com.xueqiu.android:id/md_buttonDefaultNegative")).size()>0)
        click(By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));*/
        return this;
    }
}
