package appium.page;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageObjectElement {
    public List<HashMap<String,String>> element =new ArrayList<>();
    public By getLocator(){
        String osOrigin =BasePage.driver.getCapabilities().getPlatform().toString().toLowerCase();
        return  null;
    }
    public By getLocator(String os,String version){

        //todo:xxxx.
       // HashMap<String,String> matcher = (HashMap<String, String>) element.stream().filter(x->x.get("os")==os && x.get("version")==version).toArray()[0];
        for (HashMap<String,String> matcher:element){
           if (matcher.get("os").equals(os) && matcher.get("version").equals(version)){
               if (matcher.get("id")!=null){
                   return By.id(matcher.get("id"));
               }else if (matcher.get("xpath")!=null){
                   return By.xpath(matcher.get("xpath"));
               }else if (matcher.get("aid")!=null){
                   return MobileBy.AccessibilityId(matcher.get("aid"));
               }
           }
        }
        return  null;
    }
}
