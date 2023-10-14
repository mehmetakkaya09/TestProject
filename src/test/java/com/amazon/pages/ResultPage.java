package com.amazon.pages;

import com.amazon.utility.BrowserUtility;
import com.amazon.utility.Driver;
import com.amazon.utility.TextUtility;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultPage extends BasePage {
    @FindBy(xpath = "//span[.='Results']")
    public WebElement results;

    @FindBy(xpath = "//span[.='S$50 to S$100']")
    private WebElement price50And100;

    @FindBy(xpath = "//div[@data-index='3']//a")
    private WebElement firstGlass;

    @FindBy(xpath = "//div[@data-index]")
    public List<WebElement> getNumberOfGlasses;

    /**
     * gets the product price as String and returns it as double.
     * randomly clicks on a different product each time among the first 50 products
     * @return double
     */
    public double selectRandomProductAndItsPrice(){
        Faker faker = new Faker();
        int randomNumber = faker.number().numberBetween(3,50);
        String xpath = "//div[@data-index='"+randomNumber+"']//a";
        String xpath2 = "//div[@data-index='"+randomNumber+"']//span[@class='a-price-whole']";
        //cancelButton.click();
        BrowserUtility.sleep(1);
        WebElement sunGlass = Driver.getDriver().findElement(By.xpath(xpath));
        WebElement price = Driver.getDriver().findElement(By.xpath(xpath2));
        BrowserUtility.waitForClickable(sunGlass,5);
        double doublePrice = 0;
        try {
            doublePrice = Double.parseDouble(price.getText());
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Failed to Price");
        }
        BrowserUtility.clickWithJS(sunGlass);
        return doublePrice;
    }

    /**
     * returns to previous page if the product price is more than $15
     * Clicks on $50 and $100
     * chooses the first product
     */
    public void priceEvaluation() {
        int price = Integer.parseInt(currentPrice.getText().replace(".",""));
        System.out.println(price);
        if (price>15) {
            BrowserUtility.sleep(1);
            BrowserUtility.clickWithJS(searchButton);
            TextUtility.writeToFile(String.valueOf(getNumberOfGlasses.size()));
            price50And100.click();
            firstGlass.click();
        }
    }
}
