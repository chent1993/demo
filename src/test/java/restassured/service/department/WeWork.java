package restassured.service.department;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class WeWork {
    //static可能导致多线程时出错，最好使用单例
    private static WeWork work ;
    public static WeWork getInstance(){
        if (work ==null){
            synchronized(WeWork.class){
                if (work ==null){
                    work = new WeWork();
                }
            }
        }
        return work;
    }
    String token;
    public String getToken(){
        if(token ==null) {
            token = given()
                    .param("corpid", "wwdbab80f3359105f4").param("corpsecret", "HZ4jjqlrKMQzhfklDsSKL7qQpjihzYbcM8zP6R6k5BY")
                    //.param("corpid","wwdbab80f3359105f4").param("corpsecret","HZ4jjqlrKMQzhfklDsSKL84HCkePquBPGRn1aef9L94")
                    .when()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then()
                    /*.log().all()*/.body("errcode", equalTo(0))
                    .extract()
                    .response().path("access_token");
        }
        return token;
    }
}
