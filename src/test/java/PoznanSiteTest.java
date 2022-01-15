import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

public class PoznanSiteTest {

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

        String postalCode = $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[1]/td[2]")).getText();
        String localGovernment = $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[3]/td[2]/div/div/a")).getText();
        String electoralCircuit = $(By.xpath("/html/body/div[2]/div[2]/div[1]/div[6]/div/div[1]/div/table[1]/tbody/tr[2]/td[2]/ul/li[1]/a")).getText();
        closeWindow();

        Assertions.assertEquals(postalCode, "61-614");
        Assertions.assertEquals(localGovernment, "Morasko-Radojewo");
        Assertions.assertEquals(electoralCircuit, "227");
    }

    @Test
    public void getSearchResult() {
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(byClassName("search-container")).click();
        $(By.xpath("/html/body/div[1]/header/div/nav/div[1]/div[5]/form/fieldset/input")).click();
        $(byName("string")).setValue("auto").pressEnter();
        $(byId("search_counter")).equals("Znaleziono 867 wyników");
        $(By.xpath("/html/body/section/div/div/div[2]/div/div/form/div[2]/fieldset/div[2]/div[1]/span[2]/a")).click();
        $(byId("criteria_obiekty")).click();
        $(byId("criteria_klasy")).click();
        $(byId("criteria_wiadmag")).click();
        $(byId("criteria_oswiata")).click();
        $(byId("criteria_lucene")).click();
        $(byId("criteria_wydarzenia")).click();
        $(byId("criteria_kina")).click();
        $(byId("criteria_teatry")).click();
        $(byClassName("FormButton")).click();
        $(byId("search_counter")).equals("Znaleziono 26 wyników");
        closeWindow();
    }

    @Test
    public void checkCityPresident(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(By.xpath("/html/body/section/div/div/section[5]/div/div[4]/section[1]/div/div[2]/div/article[1]/a")).click();
        String text = $(By.xpath("/html/body/div[3]/div/div/div/article/ul/li[1]/p/a[1]")).getText();
        closeWindow();

        String[] splitText = text.split(" ");
        assertEquals(splitText[splitText.length-2] + " " + splitText[splitText.length-1], "Jacek Jaśkowiak");
    }

    @Test
    public void reportAnIntervention() {
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(By.xpath("/html/body/section/div/div/section[5]/div/div[3]/section[1]/div/article/p[2]/a")).click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/div[2]/div[2]/div/fieldset/label")).click();
        $(By.id("uz_kategoria")).click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/fieldset[1]/div/div[2]/select/optgroup[8]/option[9]")).click();
        $(By.id("uz_temat")).setValue("Testowe zgłoszenie");
        $(By.id("uz_tresc")).setValue("Testowe zgłoszenie");
        $(By.id("uz_email")).setValue("Testowe@zgłoszenie.com");

        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/fieldset[3]/div/div/div/button")).click();
        $(By.id("id_ulica_street_lookup")).setValue("Uniwersytetu Poznańskiego 4");
        $(By.id("id_ulica_street_submit")).click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/div[2]/button")).click();
        $(By.name("goto_summary")).click();

        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[2]/div[1]/div[2]/p")).shouldHave(Condition.text("Testowe zgłoszenie"));
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[2]/div[2]/div[2]/p")).shouldHave(Condition.text("Testowe zgłoszenie"));
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[5]/div/div[2]/p")).shouldHave(Condition.text("Testowe@zgłoszenie.com"));
        closeWindow();
    }

}
