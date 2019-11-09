package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BroadcastPage extends BasePage {
    public BroadcastPage send(String range,String title,String body,String summary,String author){

        findElement(By.linkText("选择需要发消息的应用"),3).click();
        findElement(By.className("js_app_dlg_item"),3).click();
        findElement(By.linkText("确定")).click();
        findElement(By.className("js_select_range_btn")).click();
        //js执行时间超长，
        findElement(By.id("memberSearchInput")).sendKeys(range);
        findElement(By.className("ww_searchResult_title_peopleName")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
        driver.switchTo().frame(0);
        findElement(By.className("msg_noticeEditor_frameBody")).sendKeys(body);

        //driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        //滚屏,必须可视化才能定位到
        ((JavascriptExecutor) driver).executeScript("window.scroll(0, 800)");
        findElement(By.cssSelector(".msg_edit_infoItem_textWrap"),5).click();
        //依赖上次的点击
        findElement(By.cssSelector(".js_announce_abstract_input"),5).sendKeys(summary);
        findElement(By.cssSelector(".js_amrd_sendName"),5).sendKeys(author);
        ((JavascriptExecutor) driver).executeScript("window.scroll(0, 1000)");
        //两个发送按钮
        findElement(By.linkText("发送")).click();
        findElement(By.linkText("确定"),3).click();
        return this;
    }

    public List<String> getSendMsg(){
        //findElement(By.linkText("已发送"),3).click();
        List<String> list = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".msg_history_msgList_td"));/*.forEach(element -> {
            list.add(element.getText());
        });*/
        for (WebElement element:elements){
            list.add(element.getText());
        }
        return list;
    }
}
