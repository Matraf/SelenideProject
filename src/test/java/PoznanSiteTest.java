import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.util.List;

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

        Assertions.assertEquals(postalCode, "61-614");
        Assertions.assertEquals(localGovernment, "Morasko-Radojewo");
        Assertions.assertEquals(electoralCircuit, "227");

        closeWindow();
    }

    @Test
    public void getSearchResult() {
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(byClassName("search-container")).click();
        $(By.xpath("/html/body/div[1]/header/div/nav/div[1]/div[5]/form/fieldset/input")).click();
        $(byName("string")).setValue("auto").pressEnter();
        $(byId("search_counter")).equals("Znaleziono 867 wyników");
        $(By.xpath("/html/body/section/div/div/div[2]/div/div/form/div[2]/fieldset/div[2]/div[1]/span[1]"))
                .should(Condition.text("Filtry"))
                .click();
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

        $(By.xpath("/html/body/section/div/div/section[5]/div/div[4]/section[1]/div/div[2]/div/article[1]/a"))
                .should(Condition.text("Prezydent Miasta i Zastępcy"))
                .click();

        String text = $(By.xpath("/html/body/div[3]/div/div/div/article/ul/li[1]/p/a[1]")).getText();
        String[] splitText = text.split(" ");
        assertEquals(splitText[splitText.length-2] + " " + splitText[splitText.length-1], "Jacek Jaśkowiak");

        closeWindow();
    }

    @Test
    public void reportAnIntervention() {
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(By.xpath("/html/body/section/div/div/section[5]/div/div[3]/section[1]/div/article/p[2]/a"))
                .should(Condition.text("Zgłoś interwencję"))
                .click();

        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/div[2]/div[2]/div/fieldset/label")).click();
        $(By.id("uz_kategoria")).click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/fieldset[1]/div/div[2]/select/optgroup[8]/option[9]")).click();
        $(By.id("uz_temat")).setValue("Testowe zgłoszenie");
        $(By.id("uz_tresc")).setValue("Testowe zgłoszenie");

        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form/fieldset[3]/div/div/div/button"))
                .should(Condition.text("Wskaż na planie"))
                .click();
        $(By.id("id_ulica_street_lookup")).setValue("Uniwersytetu Poznańskiego 4");
        $(By.id("id_ulica_street_submit"))
                .should(Condition.text("Wyszukaj"))
                .click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/div[2]/button"))
                .should(Condition.text("Zatwierdź"))
                .click();

        $(By.id("uz_email")).setValue("Testowe@zgłoszenie.com");

        $(By.name("goto_summary"))
                .should(Condition.text("Dalej >>"))
                .click();

        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[2]/div[1]/div[2]/p"))
                .shouldHave(Condition.text("Testowe zgłoszenie"));
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[2]/div[2]/div[2]/p"))
                .shouldHave(Condition.text("Testowe zgłoszenie"));
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div/div/form[1]/fieldset[5]/div/div[2]/p"))
                .shouldHave(Condition.text("Testowe@zgłoszenie.com"));

        closeWindow();
    }

    @Test
    public void checkNavigationMenu(){
        open("https://www.poznan.pl/");
        $(byId("cookies-button-ok")).click();

        $(By.xpath("/html/body/div[1]/header/div/nav/div[1]/div[1]/a[2]"))
                .should(Condition.text("Dla mieszkańca"))
                .click();
        $(By.xpath("/html/body/div[1]/header/div/nav/div[1]/div[1]/div/div/ul[2]/li[7]/a"))
                .should(Condition.text("Tożsamość, historia, cmentarze"))
                .click();
        $(By.xpath("/html/body/div[1]/header/div/nav/div[1]/div[1]/div/div/ul[2]/li[7]/ul/li[6]/a"))
                .should(Condition.text("Kuchnia wielkopolska"))
                .click();
        $(By.xpath("/html/body/section/div/div/section[3]/div/div/section/div/div[2]/div[3]/article/div/h3/a"))
                .should(Condition.text("Mięsa"))
                .click();
        $(By.xpath("/html/body/section[2]/div/div[2]/article/div[2]/div/div[4]/div/p[8]/a"))
                .should(Condition.text("Bigos"))
                .click();
        ElementsCollection selenideElements = $$(By.xpath("/html/body/section[2]/div/div[2]/article/div[2]/div/div[2]/div/ul"));

        List<String> strings = selenideElements.texts().stream().toList();
        String ingredientsInOneString = strings.get(0);
        String[] split = ingredientsInOneString.split("\n");
        assertEquals(split.length, 14);

        closeWindow();
    }

}
