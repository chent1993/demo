package restassured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestWework {
    public static String token ;
    static  int departId=7;
    @BeforeAll
    static void testToken(){
        //https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
       token =  given()
                .param("corpid","wwdbab80f3359105f4").param("corpsecret","HZ4jjqlrKMQzhfklDsSKL7qQpjihzYbcM8zP6R6k5BY")
        //.param("corpid","wwdbab80f3359105f4").param("corpsecret","HZ4jjqlrKMQzhfklDsSKL84HCkePquBPGRn1aef9L94")
               .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
        .then()
                /*.log().all()*/.body("errcode",equalTo(0))
        .extract()
                .response().path("access_token");
        System.out.println(token);
    }
    static String departCreatUrl="https://qyapi.weixin.qq.com/cgi-bin/department/create";
    @Test
    @DisplayName("创建部门")
    void departCreate(){
        HashMap<String,Object> hashMap =new HashMap<String,Object>();
        hashMap.put("name","田");
        hashMap.put("parentid",departId);
        given()
                .log().all()
                .queryParam("access_token",token)
                .contentType(ContentType.JSON)
                .body(hashMap)
        .when()
                .post(departCreatUrl)
        .then()
               .log().all() .body("errcode",equalTo(0));
        //todo:需要使用list接口校验，但是如果编写list的请求，会导致代码冗余带来维护成本,所以引入po思想
    }
    static String departListUrl="https://qyapi.weixin.qq.com/cgi-bin/department/list";
    @Test
    @DisplayName("获取部门列表")
    void departList(){
        given()
                .queryParam("access_token",token)
                .queryParam("id",departId)
        .when()
                .get(departListUrl)
        .then()
                .body("errcode",equalTo(0));
    }
}
