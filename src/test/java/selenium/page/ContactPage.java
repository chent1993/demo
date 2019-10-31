package selenium.page;

import org.openqa.selenium.By;

public class ContactPage extends BasePage{
    public ContactPage add(String username,String id,String phone){
        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public void list(){

    }
}
