package ru.mail.maks825.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    WebDriver driver;

    @FindBy(xpath = "//div[@class='n-product-title__text-container-top']")
    private WebElement itemTitle;

    public String getItemTitle() {
        return itemTitle.getAttribute("textContent");
    }
}
