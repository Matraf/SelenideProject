import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class PoznanSiteTest {

    public static boolean areCoockiesClicked = false;
    @BeforeAll
    public static void setup(){
        Configuration.baseUrl = "https://allegro.pl/";
        Configuration.browser = "chrome";
    }


    @Test
    public void CheckAddressInformation(){
        open("https://www.poznan.pl/");
        if(!areCoockiesClicked){
            areCoockiesClicked = true;
            $(byId("cookies-button-ok")).click();
        }
        $(byId("id_ulica_street_lookup")).setValue("Uniwersytetu Poznańskiego 4").pressEnter();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[4]/img")).click();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[4]/img")).click();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[1]/td[2]")).shouldHave(text("61-614"));
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[3]/td[2]/div/div/a")).shouldHave(text("Morasko-Radojewo"));
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[2]/td[2]/ul/li[1]/a")).shouldHave(text("227"));
    }

    @Test
    public void getSearchResult(){
        open("https://www.poznan.pl/");
        if(!areCoockiesClicked){
            areCoockiesClicked = true;
            $(byId("cookies-button-ok")).click();
        }
        $(byId("main_search")).click();
        $(byName("string")).setValue("auto").pressEnter();
        $(byId("nutch")).equals("W całym serwisie (26)");
    }
}
