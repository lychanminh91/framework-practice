package dataGrid.pageObject;

import org.openqa.selenium.WebDriver;
import dataGrid.pageUI.HomePageUI;
import base.BasePage;


public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void switchToPage(String pageNumber){
        waitForElementClickable(driver,HomePageUI.PAGE_NUMBER,pageNumber);
        clickToElement(driver,HomePageUI.PAGE_NUMBER,pageNumber);
    }

    public void sendKeysToHeaderColumnFields(String headerName,String value){
        waitForElementVisible(driver,HomePageUI.HEADER_FIELDS,headerName);
        sendKeyToElement(driver,HomePageUI.HEADER_FIELDS,value,headerName);
    }
}
