package restassured.service.department.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restassured.service.department.api.Department;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * 部门 测试用例
 */
public class TestDepartment {
    static Department department = new Department();
    @BeforeAll
    static void beforeAll(){
    //清理数据
       ArrayList<Integer> ids = department.list(department.departId).then().extract().body().path("department.findAll{d->d.parentid =="+department.departId+"}.id");
       ids.forEach(id -> department.delete(id));
    }
    @Test
    @DisplayName("获取部门列表")
    void list(){
        department.list(department.departId).then().log().all().body("errmsg",equalTo("ok"));
    }

    @Test
    @DisplayName("创建部门")
    void create(){
        String name ="部门2";
        department.create(name).then().body("errcode",equalTo(0)).log().all();
        department.list(department.departId).then().body("department.findAll{d->d.name =='"+name+"'}.id", hasSize(1));

    }

    @Test
    @DisplayName("删除部门")
    void delete(){
        int id =department.create("部门3").then().log().all().extract().body().path("id");
        department.delete(id).then().log().all().body("errcode",equalTo(0));
    }
}
