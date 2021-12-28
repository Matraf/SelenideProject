import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

public class PoznanSiteTest {

    public static boolean areCoockiesClicked = false;

    @BeforeAll
    public static void setup(){
        Configuration.browser = "chrome";
    }

    @Test
    public void checkAddressInformation(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(byId("id_ulica_street_lookup")).setValue("Uniwersytetu Poznańskiego 4").pressEnter();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[4]/img")).click();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[4]/img")).click();
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[1]/td[2]")).shouldHave(text("61-614"));
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[3]/td[2]/div/div/a")).shouldHave(text("Morasko-Radojewo"));
        $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[2]/td[2]/ul/li[1]/a")).shouldHave(text("227"));
        closeWindow();
    }

    @Test
    public void getSearchResult(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(byId("main_search")).click();
        $(byName("string")).setValue("auto").pressEnter();
        $(byId("nutch")).equals("W całym serwisie (26)");
        closeWindow();

    }

    @Test
    public void checkCityPresident(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(By.xpath("/html/body/section/div/div/section[4]/div/div[4]/section[1]/div/div[2]/div/article[1]/a")).click();
        String text = $(By.xpath("/html/body/div[3]/div/div/div/article/ul/li[1]/p/a[1]")).getText();
        String[] splitText = text.split(" ");
        assertEquals(splitText[splitText.length-2] + " " + splitText[splitText.length-1], "Jacek Jaśkowiak");
        closeWindow();
    }

}
