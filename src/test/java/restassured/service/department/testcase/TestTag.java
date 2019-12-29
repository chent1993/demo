package restassured.service.department.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restassured.service.department.api.Tag;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

public class TestTag {
    static Tag tag= new Tag();
    @BeforeAll
    static void beforeAll(){
        ArrayList<Integer> ids = tag.list().then().extract().response().path("taglist.tagid");
        ids.forEach(id->tag.delete(id));
    }

    @Test
    @DisplayName("获取标签列表")
    void  list(){
        tag.list().then().body("errmsg",equalTo("ok"));
    }

    @Test
    @DisplayName("创建标签")
    void create(){
        String tagName ="标签1";
        int id = tag.create(tagName).then().log().all().extract().response().path("tagid");
        tag.list().then().body("taglist.findAll{t->t.tagname == '"+tagName+"'}.tagid",hasSize(1));
    }

    @Test
    @DisplayName("删除标签")
    void delete(){
        String tagName ="标签2";
        int id = tag.create(tagName).then().extract().response().path("tagid");
        tag.delete(id).then().body("errmsg",equalTo("deleted"));
    }

    @Test
    @DisplayName("更新标签")
    void update(){
        String tagName ="标签3";
        int id = tag.create(tagName).then().extract().response().path("tagid");
        tag.update(tagName,id).then().body("errmsg",equalTo("updated"));
    }
}
