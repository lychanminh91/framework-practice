package nopCommerce.pageObject;

import base.BasePage;
import nopCommerce.pageUI.ProductDetailUI;
import org.openqa.selenium.WebDriver;

public class ProductDetailPO extends BasePage {

    private WebDriver driver;

    public ProductDetailPO (WebDriver driver){
        this.driver = driver;
    }

    public void clickToExpandPanelByName(String panelName) {
        String toggleIcon = getAttributeValue(driver, ProductDetailUI.TOGGLE_ICON_BY_CARD_NAME,"class",panelName);
        if(toggleIcon.contains("fa-plus")){
            clickToElement(driver, ProductDetailUI.TOGGLE_ICON_BY_CARD_NAME,panelName);
        }
    }

    public void clickToDeleteByTitle(String productTitle) {
        //handle accept Alert in this method

    }

    public void uploadPictureByFileName(String panelName, String fileNames) {
        //waitForElementClickable(driver, ProductDetailUI.UPLOAD_FILE_BUTTON_BY_CARD_NAME,panelName);
        uploadMultipleFiles(driver,panelName,fileNames);
    }

    public void enterToAltTextBox(String value) {
        waitForElementVisible(driver, ProductDetailUI.ALT_TEXTBOX_ADD_NEW);
        sendKeyToElement(driver,ProductDetailUI.ALT_TEXTBOX_ADD_NEW,value);
    }

    public void enterToTitleTextBox(String value) {
        waitForElementVisible(driver, ProductDetailUI.TITLE_TEXTBOX_ADD_NEW);
        sendKeyToElement(driver,ProductDetailUI.TITLE_TEXTBOX_ADD_NEW,value);
    }

    public void enterToDisplayOrderTextBox(String panelName,float value) {
        waitForElementClickable(driver, ProductDetailUI.INCREASE_VALUE_BOX_BY_PANEL_NAME,panelName);
       float count = 0;
       while (count < value){
            clickToElement(driver, ProductDetailUI.INCREASE_VALUE_BOX_BY_PANEL_NAME,panelName);
            count++;
       }
    }

    public void clickAddProductPictureButton() {
        waitForElementClickable(driver, ProductDetailUI.ADD_PRODUCT_PICTURE_BUTTON);
        clickToElement(driver, ProductDetailUI.ADD_PRODUCT_PICTURE_BUTTON);
    }

    public ProductSearchPO clickToSave() {
        waitForElementClickable(driver, ProductDetailUI.SAVE_BUTTON);
        clickToElement(driver, ProductDetailUI.SAVE_BUTTON);
        return new ProductSearchPO(driver);
    }

    public boolean isPictureUploadedByFileName(String fileName) {
        fileName = fileName.split("\\.")[0].toLowerCase();
        waitForElementVisible(driver, ProductDetailUI.PRODUCT_PICTURE_BLOCK_BY_FILE_NAME,fileName);
        return isElementDisplayed(driver, ProductDetailUI.PRODUCT_PICTURE_BLOCK_BY_FILE_NAME,fileName);
    }
}
