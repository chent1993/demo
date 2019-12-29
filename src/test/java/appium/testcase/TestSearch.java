package appium.testcase;

import appium.page.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@RunWith(Parameterized.class)
public class TestSearch {
    public static appium.page.searchPage searchPage;
    @BeforeClass
    public static void beforeAll() throws MalformedURLException {
        App.start();

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws IOException {
       /* return Arrays.asList(new Object[][] {
                { "alibaba", 100f },
                { "xiaomi", 8f },
                { "jd", 33f }
        });*/
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] demo= mapper.readValue(TestSearch.class.getResourceAsStream("/appium/testcase/TestStock.yaml"),
                Object[][].class);
       return Arrays.asList(demo);
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Double price;

    @Before
    public void before(){
        searchPage=App.toSearch();
    }
    @Test
    public void search(){
        assertThat(searchPage.search(stock).getCurrentPrice(), greaterThanOrEqualTo(price.floatValue()));
    }

    @After
    public void after(){
        searchPage.cancel();
    }


}
