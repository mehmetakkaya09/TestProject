Feature: cart test

  Background:

    Given user goes to Amazon main page

    Scenario: cart test

    When User enters "glasses" in the search box and clicks search button
    Then Validate that user sees search results
    When User deletes the word entered in the search box
    And User enters "sun glasses" in the search box and presses the enter key on the keyboard.
    And User selects a random product from the products on display, according to the results.
    And User goes back to the search screen, if the price is more than $fifteen, selects the price range $fifty to $hundred and searches again. User selects the first option
    And The number of products listed on the product search page (listedProductCount), the product information of the selected product (selectedProduct) and the amount information (currentPrice) are written to the txt file.
    And User adds the selected product to the cart.
    Then Validate that the price on the product page (currentPrice) and the price of the product in the cart are compared.
    Then Validate that by increasing the quantity, it is verified that the product quantity is 2.