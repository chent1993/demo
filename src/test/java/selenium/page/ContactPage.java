package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class ContactPage extends BasePage{
    public ContactPage add(String username,String id,String phone){
        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }
    public  ContactPage  delete(String keyword){
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(keyword);
        try {
            waitClickable(By.linkText("禁用"), 5);
        }catch (Exception e){
            System.out.println("not found");
            return this;
        }
        findElement(By.className("js_del_member")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.id("clearMemberSearchInput"));
        return this;
    }
    public ContactPage deleteCurrentPage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitClickable(By.cssSelector(".ww_checkbox"),5);
        List<WebElement> webElements = driver.findElements(By.cssSelector(".ww_checkbox"));
        for (int i=1;i<webElements.size();i++){
            webElements.get(i).click();
        }
        //findElement(By.linkText("删除")).click();
        return this;
    }

    public void importFromFile(){
        findElement(By.partialLinkText("批量导入/导出"),2).click();
        findElement(By.partialLinkText("文件导入"),2).click();
        findElement(By.id("js_upload_file_input")).sendKeys("D:\\通讯录批量导入模板.xlsx");
        findElement(By.id("submit_csv")).click();
        findElement(By.linkText("完成")).click();
    }
    public void list(){

    }

    public HashMap<String,String> getUserInfo(String keyword){
        return null;
    }
}
