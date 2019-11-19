package appium.testcase;

import appium.page.App;
import appium.page.BasePage;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.Arrays;

public class TestSteps {

    @Test
    void testSteps(){
        try {
            App.start();
            BasePage basePage = new BasePage();
            basePage.parseSteps("search");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void demo(){
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(stack->{
            System.out.println(stack.getMethodName());
        });
    }
}
