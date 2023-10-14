package com.amazon.pages;

import com.amazon.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {


    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;
    @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;
    @FindBy(id = "sp-cc-rejectall-link")
    public WebElement cancelButton;
    @FindBy(xpath = "//span[@class='a-price-whole']")
    public WebElement currentPrice;
}
