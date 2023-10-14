package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;

public class CartPage extends BasePage{

    @FindBy(xpath = "//*[@*='a-size-small a-color-price sc-product-scarcity']")
    public WebElement quantityOfProductText;

    @FindBy(id = "quantity")
    public WebElement quantityDropdown;

    @FindBy(xpath = "//*[@*='a-dropdown-prompt']")
    public WebElement lastQuantity;

    /**
     * finds and returns the number of quantities among the chars
     * @return
     */
    public int quantity(){
        int quantity = 0;
        for (char s : quantityOfProductText.getText().toCharArray()) {
            if (Character.isDigit(s)) {
                quantity = Integer.parseInt(s+"");
            }
        }
        return quantity;
    }

}
