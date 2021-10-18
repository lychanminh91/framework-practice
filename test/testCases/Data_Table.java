package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dataGrid.pageObject.HomePageObject;
import dataGrid.pageObject.PageGeneratorManager;
import dataGrid.pageUI.HomePageUI;
import utils.BaseTest;

public class Data_Table extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeTest(String browserName, String appUrl){

        driver = getBrowserDriver(browserName,appUrl);

    }

    @Test
    public void TC_01(){
    homePage = PageGeneratorManager.getHomePage(driver);
    homePage.switchToPage("5");
    Assert.assertTrue(homePage.isElementDisplayed(driver,HomePageUI.PAGE_NUMBER_ACTIVATED,"5"),"Switch page successfully");

    }


    @Test
    public void TC_02(){

    homePage.sendKeysToHeaderColumnFields("Females","533469");
    homePage.sendKeyToElement(driver,HomePageUI.HEADER_FIELDS,Keys.ENTER.toString(),"Females");
    }

    @AfterClass
    public void afterTest(){
        homePage.sleepInSeconds(3);
        driver.quit();
    }
}
