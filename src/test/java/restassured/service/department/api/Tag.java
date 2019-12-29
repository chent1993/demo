package restassured.service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restassured.service.department.WeWork;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Tag {
    static String createTagUrl ="https://qyapi.weixin.qq.com/cgi-bin/tag/create";
    static String updateTagUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/update";
    static String deleteTagUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/delete";
    static String listTagUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/list";
    public Response create(String tagName){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("tagname",tagName);
       Response response =
       given()
              /* .log().all()*/
                .queryParam("access_token", WeWork.getInstance().getToken())
               .contentType(ContentType.JSON)
               .body(data)
        .when()
                .post(createTagUrl)
        .then()
               .log().all()
                .body("errcode",equalTo(0))
                .extract().response();

       return response;
    }

    public Response update(String tagName,int tagId){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("tagname",tagName);
        data.put("tagid",tagId);
        Response response =
                given()
                        .queryParam("access_token", WeWork.getInstance().getToken())
                        .contentType(ContentType.JSON)
                        .body(data)
                .when()
                        .post(updateTagUrl)
                .then()
                        .log().all()
                        .body("errcode",equalTo(0))
                        .extract().response();
        return response;
    }

    public  Response delete(int tagId){
        Response response =
                given()
                         .queryParam("access_token", WeWork.getInstance().getToken())
                         .queryParam("tagid",tagId)
                .when()
                        .get(deleteTagUrl)
                .then()
                        .log().all()
                        .body("errcode",equalTo(0))
                .extract().response();
        return response;
    }

    public Response list(){
        Response response =
                given()
                        .queryParam("access_token", WeWork.getInstance().getToken())
                .when()
                        .get(listTagUrl)
                .then()
                        .log().all()
                        .body("errcode",equalTo(0))
                        .extract().response();
        return response;
    }
}
