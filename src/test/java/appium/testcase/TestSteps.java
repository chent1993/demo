package appium.testcase;

import appium.page.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    @Test
    void  testPOM() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel model = new PageObjectModel();
        PageObjectElement element =new PageObjectElement();
        HashMap<String, String> map2= new HashMap<>();
        map2.put("id","aaa");
        map2.put("os","android");
        map2.put("version","1.0.1");
        element.element.add(map2);
        model.elements.put("search_locator",element);

        HashMap<String, PageObjectMethod> steps = new HashMap<>();
        PageObjectMethod stepcase = new PageObjectMethod();
        HashMap<String, String> map= new HashMap<>();
        map.put("id","aaa");
        map.put("send","xxxxx");

        List<HashMap<String, String>> list =new ArrayList<>();
        list.add(map);
        list.add(map);
        stepcase.setSteps(list);
        model.methods.put("search",stepcase);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model));

    }
}
