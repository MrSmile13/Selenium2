package ru.mail.maks825.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MarketPage {

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//a[@class='logo logo_type_link logo_part_market']")
    public WebElement marketTitle;

    @FindBy(xpath = "//a[@href='/catalog/54440?hid=198119&track=menu']")
    private WebElement electronicsLink;

    @FindBy(xpath = "//h1[@class='title title_size_32 i-bem title_changeable_yes title_js_inited']")
    public WebElement pageTitle;

    @FindBy(xpath = "//a[@href='/catalog/54726/list?hid=91491&track=fr_ctlg']")
    private WebElement mobilephonesLink;

    @FindBy(xpath = "//div[@data-recipe='499274746']")
    private WebElement filterSamsung;

    @FindBy(id = "glpricefrom")
    private WebElement priceFrom;

    @FindBy(id = "glpriceto")
    private WebElement priceTo;

    @FindBy(xpath = "//div[@class='n-filter-applied-results__content preloadable i-bem preloadable_js_inited']")
    private WebElement foundElectronics;

    @FindBy(xpath = "//a[@href='/catalog/56179/list?hid=90555&track=fr_ctlg']")
    private WebElement earphonesLink;

    public void goToSubSectionElectronics() {
        electronicsLink.click();
    }

    public void goToMobilephones() {
        mobilephonesLink.click();
    }

    public void filterBySamsung() {
        filterSamsung.click();
    }

    public void inputPriceFrom(int price) {
        priceFrom.sendKeys(Integer.toString(price));
        waitForLoadItems();
    }

    public void inputPriceTo(int price) {
        priceTo.sendKeys(Integer.toString(price));
        waitForLoadItems();
    }

    public List<WebElement> getHeaderOfItems() {
        return foundElectronics.findElements(
                By.xpath("//div[@class='n-snippet-cell2__header']//div[@class='n-snippet-cell2__title']"));
    }

    public String getTitleOfItem(int itemNumber) {
        return getHeaderOfItems().get(itemNumber).getAttribute("textContent");
    }

    public void goToItem(int itemNumber) {
        List<WebElement> items = foundElectronics.findElements(By.xpath("//div[@class='n-snippet-cell2__title']/a"));
        items.get(itemNumber).click();
    }

    public List<WebElement> getPriceOfItems() {
        return foundElectronics.findElements(By.xpath("//div[@class='n-snippet-cell2__main-price']"));
    }

    public void waitForLoadItems() {
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class='i-bem n-pager-more n-pager-more_state_hidden n-pager-more_js_inited']")));
    }

    public void goToEarphonesLink() {
        earphonesLink.click();
    }

    public void choiceManufacturer(String manufacturer) {
        driver.findElement(By.xpath("//ul[@class='_2y67xS5HuR']//li//span[text()='" + manufacturer + "']")).click();
        waitForLoadItems();
    }

}
