package restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.CoreMatchers.hasItems;

public class XueQiu {
    @Test
    void testSearch(){
        //信任此证书
        useRelaxedHTTPSValidation();
        given()
                .queryParam("code","sogo").queryParam("size",3).queryParam("page",1)
                .header("Cookie","aliyungf_tc=AQAAAOWTsgbHuAcAHpr0eCYoEJDYnI6C; acw_tc=2760820c15773640625182748ea24727dc905b6658144a448b359d45c16509; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_a_token.sig=9D7L4g9AUgO9R0B-fVrMPzIIHd0; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; xq_r_token.sig=Xff566DPwSJrAsA2Nr4ps31Qco8; xqat=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xqat.sig=tJ-oMGJijInerPj1SP9unChd09w; u=121577364084860; device_id=24700f9f1986800ab4fcc880530dd0ed; Hm_lvt_1db88642e346389874251b5a1eded6e3=1577364084,1577364093; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577364216")
        .when()
                .get("https://xueqiu.com/stock/search.json")
        .then().log().all().statusCode(200)
                .body("stocks.name",hasItems("搜狗"));//断言
    }


}
