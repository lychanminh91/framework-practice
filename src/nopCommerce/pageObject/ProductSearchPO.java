package nopCommerce.pageObject;

import base.BasePage;
import nopCommerce.pageUI.BasePageUI;
import nopCommerce.pageUI.ProductDetailUI;
import nopCommerce.pageUI.ProductSearchUI;
import org.openqa.selenium.WebDriver;

public class ProductSearchPO extends BasePage {

    private WebDriver driver;

    public ProductSearchPO (WebDriver driver){
        this.driver = driver;
    }

    public void enterProductName(String productName) {
        waitForElementVisible(driver, ProductSearchUI.PRODUCT_NAME_SEARCH_TEXT_BOX);
        sendKeyToElement(driver, ProductSearchUI.PRODUCT_NAME_SEARCH_TEXT_BOX, productName);
    }

    public void clickSearchButton() {
        waitForElementClickable(driver, ProductSearchUI.SEARCH_BUTTON);
        clickToElement(driver, ProductSearchUI.SEARCH_BUTTON);
    }

    public ProductDetailPO clickToEditButtonByProductName(String productName) {
        waitForElementClickable(driver, ProductSearchUI.EDIT_BUTTON_BY_PRODUCT_NAME,productName);
        clickToElement(driver, ProductSearchUI.EDIT_BUTTON_BY_PRODUCT_NAME,productName);
        return new ProductDetailPO(driver);
    }

    public boolean isUpdateSuccessMessageDisplayed() {
        waitForElementVisible(driver, BasePageUI.PRODUCT_UPDATE_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BasePageUI.PRODUCT_UPDATE_SUCCESS_MESSAGE);
    }
}
