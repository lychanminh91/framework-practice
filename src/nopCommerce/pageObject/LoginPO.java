package nopCommerce.pageObject;

import base.BasePage;
import nopCommerce.pageUI.LoginUI;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {

    private WebDriver driver;

    public LoginPO (WebDriver driver){
        this.driver = driver;
    }

    public void enterEmail(String email) {
        waitForElementVisible(driver, LoginUI.EMAIL_FIELD,email);
        sendKeyToElement(driver, LoginUI.EMAIL_FIELD,email);
    }

    public void enterPassword(String password) {
        waitForElementVisible(driver, LoginUI.PASSWORD_FIELD,password);
        sendKeyToElement(driver, LoginUI.PASSWORD_FIELD,password);
    }

    public DashboardPO clickToLogin() {
        waitForElementClickable(driver,LoginUI.LOG_IN_BUTTON);
        clickToElement(driver, LoginUI.LOG_IN_BUTTON);
        return  new DashboardPO(driver);
    }
}
