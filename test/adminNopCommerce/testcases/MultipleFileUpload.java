package adminNopCommerce.testcases;

import nopCommerce.pageObject.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BaseTest;

public class MultipleFileUpload extends BaseTest {
    WebDriver driver;
    LoginPO loginPO;
    DashboardPO dashboardPO;
    ProductSearchPO productSearchPO;
    ProductDetailPO productDetailPO;
    String email = "admin@yourstore.com";
    String password = "admin";
    String menu = "Catalog";
    String submenu = "Products";
    String productName = "Adobe Photoshop CS4";
    String panelName = "Pictures";
    String cardName = "pictures";
    String fileName = "adobe photoshop cs4.png";
    String altValue = "alt";
    String titleValue = "title";
    float orderValue = 5;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeTest (String browserName, String url){
        driver = getBrowserDriver(browserName,url);

        loginPO = PageGeneratorManager.getLoginPO(driver);

        loginPO.enterEmail(email);
        loginPO.enterPassword(password);
        dashboardPO = loginPO.clickToLogin();

        productSearchPO = dashboardPO.openSubMenuPageByName(menu,submenu);
        productSearchPO.enterProductName(productName);
        productSearchPO.clickSearchButton();

        productDetailPO = productSearchPO.clickToEditButtonByProductName(productName);

    }

    @Test
    public void TC_01(){

        productDetailPO.clickToExpandPanelByName(panelName);
        productDetailPO.uploadPictureByFileName(cardName,fileName);
        productDetailPO.enterToAltTextBox(altValue);
        productDetailPO.enterToTitleTextBox(titleValue);
        productDetailPO.enterToDisplayOrderTextBox(cardName,orderValue);
        productDetailPO.clickAddProductPictureButton();

        Assert.assertTrue(productDetailPO.isPictureUploadedByFileName(fileName),"Picture is uploaded successfully");
        productSearchPO = productDetailPO.clickToSave();
        Assert.assertTrue(productSearchPO.isUpdateSuccessMessageDisplayed(),"Success message displayed");

        productSearchPO.enterProductName(productName);
        productSearchPO.clickSearchButton();

        productDetailPO = productSearchPO.clickToEditButtonByProductName(productName);
        productDetailPO.clickToDeleteByTitle(titleValue);

        productSearchPO = productDetailPO.clickToSave();
        Assert.assertTrue(productSearchPO.isUpdateSuccessMessageDisplayed(),"Success message displayed");

    }

    @AfterClass
    public void afterTest(){
        productSearchPO.sleepInSeconds(5);
        driver.quit();
    }
}
