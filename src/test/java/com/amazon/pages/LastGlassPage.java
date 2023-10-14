package com.amazon.pages;

import com.amazon.utility.BrowserUtility;
import com.amazon.utility.Driver;
import com.amazon.utility.TextUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LastGlassPage extends BasePage{
    @FindBy(xpath = "//li[@class='a-spacing-mini']/span")
    public List<WebElement> infos;

    @FindBy(xpath = "//label[.='Size Name:']")
    public WebElement sizeName;

    @FindBy(css = "select[name='dropdown_selected_size_name']")
    public WebElement dropDown;

    @FindBy(css = "#add-to-cart-button")
    public WebElement addToCartButton;

    /**
     * writes product information to a .txt file
     */
    public void writeProductInfoToFile() {
        for (WebElement info : infos) {
            TextUtility.writeToFile(info.getText());
        }
    }

    /**
     * if the addToCart option is not active, it goes through the entire dropdown and selects the first available option
     */
    public void addToCart(){
        int count = 1;
        while (addToCartButton.getAttribute("style").equals("cursor: not-allowed;")) {
            BrowserUtility.sleep(2);
            Select s = new Select(dropDown);
            s.selectByIndex(count++);
        }

    }
}
