package unit;


import appium.page.TestCaseSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestResources {
    public String name;
    public int age;
    @Test
    void readFile(){
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/appium/testcase/TestStock.yaml"));

        try {
            System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/appium/testcase/TestStock.yaml").getPath()), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写json
     * @throws IOException
     */
    @Test
    void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("deom.json"),this);
    }

    /**
     * 读json
     * @throws IOException
     */
    @Test
    void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(new File("deom.json"),this.getClass());
    }

    /**
     * 读yaml
     * @throws IOException
     */
    @Test
    void readYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] demo = mapper.readValue(this.getClass().getResource("/appium/testcase/TestStock.yaml"),
                Object[][].class);
        System.out.println(demo[0][0]);
    }

    //数据读取
    //步骤读取
    //类读取
    @Test
    void  demo(){
        System.out.println("/"+this.getClass().getCanonicalName().replace(".","/"));
    }

    @Test
    void steps() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HashMap<String, TestCaseSteps> steps = new HashMap<>();
        TestCaseSteps stepcase = new TestCaseSteps();
        HashMap<String, String> map= new HashMap<>();
        map.put("id","aaa");
        map.put("send","xxxxx");

        List<HashMap<String, String>> list =new ArrayList<>();
        list.add(map);
        list.add(map);
        stepcase.setSteps(list);
        steps.put("search",stepcase);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(steps));
    }

}
