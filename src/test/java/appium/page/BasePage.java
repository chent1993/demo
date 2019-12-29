package appium.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static AndroidDriver driver;

    /**
     * 测试步骤参数化
     */
    private HashMap<String,Object> params=new HashMap<>();

    /**
     * 测试步骤参数读取
     */
    private HashMap<String,Object> results=new HashMap<>();

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public HashMap<String, Object> getResults() {
        return results;
    }
    private PageObjectModel model=new PageObjectModel();

    /**
     * 通用元素定位和异常处理机制
     * @param by
     * @return
     */
    public WebElement findElement(By by){
        System.out.println(by);
        try {
           return driver.findElement(by);
        }catch (Exception e){
           handleAlert();
           return driver.findElement(by);
        }
    }
    public static void click(By by) {
        //todo: 递归是更好的
        System.out.println(by);
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlert();

            driver.findElement(by).click();
        }
    }

    public List<WebElement> findElements(By by){
        System.out.println(by);
        try {
            return driver.findElements(by);
        } catch (Exception e) {
           // handleAlert();
            return driver.findElements(by);
        }
    }
    public static void handleAlert(){
        List<By> alertBoxs = new ArrayList<By>();
        //todo: 不需要所有的都判断是否存在
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        By tips = By.id("com.xueqiu.android:id/snb_tip_text");
        alertBoxs.add(tips);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        for (By alert:alertBoxs){
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if(alert.equals(tips)){
                Dimension size = driver.manage().window().getSize();
               try {
                   //某些元素是动态变化的，等点击元素时查询到的元素可能已经变化
                   //需要在点击之前再判断一次
                   if (driver.findElements(tips).size()>=1){
                       new TouchAction(driver).tap(PointOption.point(size.width/2,size.height/2)).perform();
                   }
               }catch (Exception e){
                    e.printStackTrace();
               }
            }else  if (ads.size() >= 1) {
                ads.get(0).click();
            }
        }
       /* alertBoxs.forEach(alert -> {
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size() >= 1) {
                ads.get(0).click();
            }
        });*/
    }
    private static void handleAlertByPageSource(){
        //todo: xpath匹配， 标记 定位
        String xml=driver.getPageSource();
        List<String> alertBoxs=new ArrayList<String>();
        alertBoxs.add("xxx");
        alertBoxs.add("yyy");
        for (String alert:alertBoxs){
            if(xml.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        }
        /*alertBoxs.forEach(alert -> {
            if(xml.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        })*/;

    }

    /**
     * 解析步骤
     * @param method
     */
    public void parseSteps(String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = "/"+this.getClass().getCanonicalName().replace(".","/")+".yaml";
                /*TypeReference<HashMap<String, PageObjectMethod>> trf = new TypeReference<HashMap<String, PageObjectMethod>>(){};
                HashMap<String, PageObjectMethod> steps = mapper.readValue(this.getClass().getResourceAsStream(path),
                         trf);*/
                /*PageObjectModel model = mapper.readValue(this.getClass().getResourceAsStream(path),PageObjectModel.class);
                System.out.println(model.methods.get(method));
                parseSteps(model.methods.get(method));*/
            parseSteps(path,method);

    }

    public void parseSteps(){
        parseSteps(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    public void parseSteps(String path,String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
       // TypeReference<HashMap<String, PageObjectMethod>> trf = new TypeReference<HashMap<String, PageObjectMethod>>(){};
        try {
           /* HashMap<String, PageObjectMethod> steps = mapper.readValue(BasePage.class.getResourceAsStream(path),
                    trf);*/
           /* PageObjectModel */model = mapper.readValue(PageObjectModel.class.getResourceAsStream(path),PageObjectModel.class);
            parseSteps(model.methods.get(method));
            //parseSteps(steps.get(method));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void parseModel(PageObjectModel model){
        model.methods.forEach(method->{

                }
        );
    }*/
    private void parseElement(){

    }
    private void parseSteps(PageObjectMethod steps){
        steps.getSteps().forEach(step -> {
            WebElement element=null;
            if (step.get("id")!=null){
                element=driver.findElement(By.id(step.get("id")));
            }else if (step.get("xpath")!=null){
                element=driver.findElement(By.xpath(step.get("xpath")));
            }else if (step.get("aid")!=null){
                element=driver.findElement(MobileBy.AccessibilityId(step.get("aid")));
            }else if(step.get("element")!=null){//获取element元素
                System.out.println(model.elements.get(step.get("element").toString()));
                element= driver.findElement(model.elements.get(step.get("element")).getLocator("android","4.5"));
            }

            if (step.get("send")!=null){
                String send =step.get("send");
                //配置文件中解决变量替换
                for (Map.Entry<String,Object> kv:params.entrySet()){
                    if(send.contains("{"+kv.getKey()+"}"))
                        send = send.replace("{"+kv.getKey()+"}",kv.getValue().toString());
                }
                element.sendKeys(send);
            }else if(step.get("get")!=null){
                String text = element.getAttribute(step.get("get"));
                results.put(step.get("dump"),text);
            }else {
                element.click();
            }
        });
    }


}
