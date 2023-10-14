package com.amazon.step_definitions;

import com.amazon.pages.CartPage;
import com.amazon.pages.LastGlassPage;
import com.amazon.pages.ResultPage;
import com.amazon.utility.BrowserUtility;
import com.amazon.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class Cart_StepDefs {

    ResultPage resultPage = new ResultPage();
    LastGlassPage lastGlassPage = new LastGlassPage();

    CartPage cartPage = new CartPage();
    String currentPrice = "";

    @Given("user goes to Amazon main page")
    public void user_goes_to_amazon_main_page() {
        Assert.assertEquals(Driver.getDriver().getTitle(), "Amazon.sg: Shop Online for Electronics, Computers, Books, Toys, DVDs, Baby, Grocery, & more");
    }

    @When("User enters {string} in the search box and clicks search button")
    public void user_enters_in_the_search_box_and_clicks_search_button(String glasses) {
        BrowserUtility.waitForClickable(resultPage.searchBox, 10);
        BrowserUtility.sleep(2);
        resultPage.searchBox.sendKeys(glasses);
        resultPage.searchButton.click();
    }

    @Then("Validate that user sees search results")
    public void validate_that_user_sees_search_results() {
        Assert.assertTrue(resultPage.results.isDisplayed());
    }

    @When("User deletes the word entered in the search box")
    public void user_deletes_the_word_entered_in_the_search_box() {
        resultPage.searchBox.clear();
    }

    @When("User enters {string} in the search box and presses the enter key on the keyboard.")
    public void user_enters_in_the_search_box_and_presses_the_key_on_the_keyboard(String sunglasses) {
        resultPage.searchBox.sendKeys(sunglasses + Keys.ENTER);
    }

    @When("User selects a random product from the products on display, according to the results.")
    public void user_selects_a_random_product_from_the_products_on_display_according_to_the_results() {
        resultPage.selectRandomProductAndItsPrice();
    }

    @When("User goes back to the search screen, if the price is more than $fifteen, selects the price range $fifty to $hundred and searches again. User selects the first option")
    public void user_goes_back_to_the_search_screen_if_the_price_is_more_than_$_selects_the_price_range_and_searches_again_user_selects_the_first_option() {
        resultPage.priceEvaluation();
    }

    @When("The number of products listed on the product search page \\(listedProductCount), the product information of the selected product \\(selectedProduct) and the amount information \\(currentPrice) are written to the txt file.")
    public void the_number_of_products_listed_on_the_product_search_page_listed_product_count_the_product_information_of_the_selected_product_selected_product_and_the_amount_information_current_price_are_written_to_the_txt_file() {
        lastGlassPage.writeProductInfoToFile();
    }

    @When("User adds the selected product to the cart.")
    public void user_adds_the_selected_product_to_the_cart() {
        lastGlassPage.addToCart();
        currentPrice = lastGlassPage.currentPrice.getText().replace(".", "");
        BrowserUtility.clickWithJS(lastGlassPage.addToCartButton);
    }

    @When("Validate that the price on the product page \\(currentPrice) and the price of the product in the cart are compared.")
    public void validate_that_user_the_price_on_the_product_page_current_price_and_the_price_of_the_product_in_the_cart_are_compared() {
        Assert.assertEquals(currentPrice, cartPage.currentPrice.getText().replace(".", ""));
    }

    @When("Validate that by increasing the quantity, it is verified that the product quantity is {int}.")
    public void validate_that_by_increasing_the_quantity_it_is_verified_that_the_product_quantity_is(int number) {
        if (cartPage.quantity() > 1) {
            Select s = new Select(cartPage.quantityDropdown);
            s.selectByValue("2");
            Assert.assertEquals(number, Integer.parseInt(cartPage.lastQuantity.getText()));
        }
    }
}
