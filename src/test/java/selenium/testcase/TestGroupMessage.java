package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGroupMessage {
    public static App app;
    @BeforeClass
    public static void beforeAll(){

        app=new App();
        app.loginWithCookie();

    }

    @Test
    public void send(){
        List<String> list = app.toBroadcast()
                .send("18888888889","快递来了","您的快递已到","快递","Test")
                .getSendMsg();
        assertThat(list,hasItem("快递来了"));
    }

    @AfterClass
    public static void  afterAll(){
        app.quit();
    }
}
