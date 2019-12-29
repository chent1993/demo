package restassured.service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restassured.service.department.WeWork;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Department {
    public static  int departId=7;
    static String departCreatUrl="https://qyapi.weixin.qq.com/cgi-bin/department/create";
    static String departListUrl="https://qyapi.weixin.qq.com/cgi-bin/department/list";
    static String departDelUrl ="https://qyapi.weixin.qq.com/cgi-bin/department/delete";
    public Response list(int id){
        Response response =given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("id",departId)
                .when()
                .get(departListUrl)
                .then()
                .body("errcode",equalTo(0))
                .extract().response();
        return response;
    }

    public Response create(String departMane){

        HashMap<String,Object> hashMap =new HashMap<String,Object>();
        hashMap.put("name",departMane);
        hashMap.put("parentid",departId);
        Response response =given()
                .log().all()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(hashMap)
                .when()
                .post(departCreatUrl)
                .then()
                .extract().response();
        return response;
    }

    public Response delete(int id){
        Response response =given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("id",id)
        .when()
                .get(departDelUrl)
        .then()
                .body("errcode",equalTo(0))
        .extract().response();
        return response;
    }
}
