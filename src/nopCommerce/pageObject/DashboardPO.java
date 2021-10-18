package nopCommerce.pageObject;

import base.BasePage;
import nopCommerce.pageUI.BasePageUI;
import org.openqa.selenium.WebDriver;

public class DashboardPO extends BasePage {

    private WebDriver driver;

    public DashboardPO (WebDriver driver){
        this.driver = driver;
    }


    public ProductSearchPO openSubMenuPageByName(String menuItem, String subMenuItem) {
        waitForElementClickable(driver, BasePageUI.MENU_ITEMS,menuItem);
        clickToElement(driver,BasePageUI.MENU_ITEMS,menuItem);
        waitForElementClickable(driver, BasePageUI.SUB_MENU_ITEMS, subMenuItem);
        clickToElement(driver,BasePageUI.SUB_MENU_ITEMS, subMenuItem);
        return new ProductSearchPO(driver);
    }
}
