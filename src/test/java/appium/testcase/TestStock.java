package appium.testcase;

import appium.page.App;
import appium.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestStock {
    private static StockPage stockPage;
    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        App.start();
        stockPage =App.toStocks();
    }

    @Order(1)
    @Test
    void addDefaultedStocks(){
      if (stockPage.getAllStocks().size()>=1){
          stockPage.deleteAll();
      }
      assertThat(stockPage.addDefultStocks().getAllStocks().size(),greaterThanOrEqualTo(6));
    }

    @Order(2)
    @ParameterizedTest
    //@ValueSource(strings ={"pdd","jd"})//单个参数，使用不多
    @MethodSource("data")
    void  addStock(String code,String name){
       stockPage.toSearch().search(code).addSearch().cancel();
        assertThat(stockPage.getAllStocks(),hasItem(name));
    }

    public static Stream<Arguments> data(){
        return Stream.of(
              arguments("pdd","拼多多"),
              arguments("jingdong","京东")
                );
    }
}
