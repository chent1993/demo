package com.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.equalTo;

public class AllureDemoTest {
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Test
    @DisplayName("demo1 displayName")
    @Description("Allure test first demo description")
    @Link("http://testerhome.com/")
    @Issue("110")//编号
    public void demo1() throws Exception{
        Allure.addAttachment("demo picture", "image/jpg",new FileInputStream("E:\\picture.jpg"),"jpg");

        error.checkThat(1,equalTo(2));
    }
    @Test
    public void demo2(){
        error.checkThat(1,equalTo(2));
    }
    @Test
    public void demo3(){
        error.checkThat(1,equalTo(1));
    }
}
