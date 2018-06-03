package ru.mail.maks825.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//a[@href='https://market.yandex.ru/?clid=505&utm_source=face_abovesearch&utm_campaign=face_abovesearch']")
    public WebElement marketLink;

    public void goToMarket() {
        marketLink.click();
    }
}
