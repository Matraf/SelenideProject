import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PoznanSiteTest {

    @BeforeAll
    public static void setup(){
        Configuration.baseUrl = "https://allegro.pl/";
        Configuration.browser = "chrome";
    }

    @Test
    public void getSearchResult(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();
        $(byId("main_search")).click();
        $(byName("string")).setValue("auto").pressEnter();
        $(byId("nutch")).equals("W całym serwisie (26)");
    }
}
