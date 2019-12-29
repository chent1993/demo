package restassured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Demo {

    @Test
    void testPostJson(){
        HashMap<String,Object> map= new HashMap<String,Object>();
        map.put("a",1);
        map.put("b","testerhome");
        map.put("array",new String[]{"111","222"});
        given()
                .contentType(ContentType.JSON)
                .body(map)
        .when().post("http://www.baidu.com/")
        .then()
                .log().all()
                .statusCode(200);
    }
}
