package ru.mail.maks825.tests;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.mail.maks825.pages.ItemPage;
import ru.mail.maks825.pages.MainPage;
import ru.mail.maks825.pages.MarketPage;
import utils.ConfigProperties;

import java.util.List;
import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EarphonesTest {

    public static WebDriver driver;
    public static ItemPage itemPage;
    public static MainPage mainPage;
    public static MarketPage marketPage;
    private static String titleOfFirstEarphone;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromeDriver"));
        driver = new ChromeDriver();
        itemPage = new ItemPage(driver);
        mainPage = new MainPage(driver);
        marketPage = new MarketPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @Test
    public void step1_openYandexWebPage() {
        driver.get(ConfigProperties.getProperty("mainURL"));
        Assert.assertEquals("https://www.yandex.ru/", driver.getCurrentUrl());
    }

    @Test
    public void step2_goToMarketPage() {
        mainPage.goToMarket();
        Assert.assertEquals("Маркет", marketPage.marketTitle.getAttribute("textContent"));
        Assert.assertTrue(driver.getCurrentUrl().contains("https://market.yandex.ru/"));
    }

    @Test
    public void step3_goToElectronics() {
        marketPage.goToSubSectionElectronics();
        Assert.assertEquals("Электроника", marketPage.pageTitle.getAttribute("textContent"));

        marketPage.goToEarphonesLink();
        Assert.assertEquals("Наушники и Bluetooth-гарнитуры",
                marketPage.pageTitle.getAttribute("textContent"));
    }

    @Test
    public void step4_filterByPrice() {
        marketPage.inputPriceFrom(17000);
        marketPage.inputPriceTo(25000);

        List<WebElement> prices = marketPage.getPriceOfItems();
        try {
            for (WebElement price : prices) {
                int formattedPrice = Integer.parseInt(price.getAttribute("textContent")
                                                        .replaceAll("[^0-9]", ""));
                Assert.assertTrue(formattedPrice >= 17000 && formattedPrice <= 25000);
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Ошибка преобразования цены в цифру");
            nfe.printStackTrace();
        }
    }

    @Test
    public void step5_chooseFilterBeats() {
        marketPage.choiceManufacturer("Beats");

        Assert.assertEquals("Наушники и Bluetooth-гарнитуры Beats",
                marketPage.pageTitle.getAttribute("textContent"));

        List<WebElement> headers = marketPage.getHeaderOfItems();
        for (WebElement header : headers)
            Assert.assertTrue(header.getAttribute("textContent").contains("Beats"));

        titleOfFirstEarphone = marketPage.getTitleOfItem(0);
    }

    @Test
    public void step6_goToFirstEarphone() {
        marketPage.goToItem(0);
        Assert.assertEquals(titleOfFirstEarphone, itemPage.getItemTitle());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
