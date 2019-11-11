package appium.testcase;

import appium.page.App;
import appium.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class TestStock {
    private static StockPage stockPage;
    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        App.start();
        stockPage =App.toStocks();
    }

    @Test
    void addDefaultedStocks(){
      if (stockPage.getAllStocks().size()>=1){
          stockPage.deleteAll();
      }
        stockPage.addDefultStocks();
    }
}
