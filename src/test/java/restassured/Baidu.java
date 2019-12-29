package restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Baidu {
    @Test
    void testGetHtml(){
        given()
                .log().all().get("http://www.baidu.com/")
                .then().log().all().statusCode(200);
    }

    @Test
    void testMP3(){
        given()
                .queryParam("wd","mp3")
        .when()
                .get("http://www.baidu.com/")
        .then()
                .log().all().statusCode(200);
    }
}
