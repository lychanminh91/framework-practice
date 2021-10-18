package base;

import nopCommerce.pageUI.ProductDetailUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.GlobalConstants;

import java.io.File;
import java.util.List;
import java.util.Set;

public class BasePage {
    WebDriverWait explicitWait;
    Alert alert;
    long timeout = 30;
    Select select;
    Actions action;
    JavascriptExecutor jsExecutor;

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getPageUrl(WebDriver driver, String url){
        driver.get(url);
    }

    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void navigateBack(WebDriver driver){
        driver.navigate().back();
    }

    public void navigateForward(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver){
        explicitWait = new WebDriverWait(driver,timeout);
        return alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }

    public void sendKeysToAlert(WebDriver driver, String value){
        waitForAlertPresence(driver).sendKeys(value);
    }

    public String getTextFromAlert(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }

    public void switchToWindowById(WebDriver driver, String parentId){
        Set<String> allWindowIds = driver.getWindowHandles();
        for(String windowId : allWindowIds){
            if(!windowId.equals(parentId)){
                driver.switchTo().window(windowId);
                break;
            }
        }
    }

    public void switchToWindowByTitle( WebDriver driver, String title){
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String windowId : allWindowIds){
            String currentWinTitle = driver.switchTo().window(windowId).getTitle();
            if(currentWinTitle.equals(title)){
                break;
            }
        }
    }

    public boolean closeAllWindowExceptParent(WebDriver driver, String parentId){
        Set<String> allWindowIds = driver.getWindowHandles();
        for(String windowId : allWindowIds){
            if(!windowId.equals(parentId)){
                driver.switchTo().window(windowId);
                driver.close();
            }
            driver.switchTo().window(parentId);
        }
        return driver.getWindowHandles().size() == 1;
    }

    public WebElement getElementByXpath(WebDriver driver,String locator){
        return driver.findElement(getXpath(locator));
    }

    public WebElement getElementByXpath(WebDriver driver,String locator, String... params){
        return driver.findElement(getXpath(String.format(locator,(Object[]) params)));
    }

    public List<WebElement> getElementsByXpath(WebDriver driver,String locator){
        return driver.findElements(getXpath(locator));
    }

    public By getXpath(String locator){
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver,String locator, String... params){

        getElementByXpath(driver,String.format(locator, (Object[]) params)).click();
    }

    public void sendKeyToElement(WebDriver driver,String locator, String value, String... params){
        getElementByXpath(driver,String.format(locator, (Object[]) params)).clear();
        getElementByXpath(driver,String.format(locator, (Object[]) params)).sendKeys(value);
    }

    public void selectItemInDropDownByText(WebDriver driver, String locator,String title){
        select = new Select(getElementByXpath(driver,locator));
        select.selectByValue(title);
    }

    public WebElement getSelectedItemInDropdown(WebDriver driver,String locator){
        select = new Select(getElementByXpath(driver,locator));
        return select.getFirstSelectedOption();
    }

    public boolean isDropdownMultiple(WebDriver driver,String locator){
        select = new Select(getElementByXpath(driver,locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver,String parentXpath, String allItemXpath, String expectedText){
        clickToElement(driver,parentXpath);
        sleepInSeconds(2);
        explicitWait = new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpath(allItemXpath)));
        List<WebElement> allItems = getElementsByXpath(driver,allItemXpath);
        for(WebElement item : allItems){
            if(item.getText().equals(expectedText)){
                item.click();
                break;
            }
        }
        Assert.assertEquals(getElementByXpath(driver,parentXpath).getText(),expectedText);
    }

    public String getAttributeValue(WebDriver driver,String locator, String attributeName, String... params){
        return getElementByXpath(driver,String.format(locator,(Object[]) params)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver,String locator){
        return getElementByXpath(driver,locator).getText();
    }

    public int getElementSize(WebDriver driver,String locator){
        return getElementsByXpath(driver,locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver,String locator){
        if(!getElementByXpath(driver,locator).isSelected()){
            getElementByXpath(driver,locator).click();
        }
    }

    public void uncheckCheckBox(WebDriver driver,String locator){
        if(getElementByXpath(driver,locator).isSelected()){
            getElementByXpath(driver,locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver,String locator,String... params){
        return getElementByXpath(driver,String.format(locator,  (Object[]) params)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver,String locator){
        return getElementByXpath(driver,locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver,String locator){
        return getElementByXpath(driver,locator).isSelected();
    }

    public WebDriver switchToIframe(WebDriver driver,String locator){
        return driver.switchTo().frame(getElementByXpath(driver,locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver,String locator){
        return driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver,String locator){
        action = new Actions(driver);
        action.doubleClick(getElementByXpath(driver,locator)).perform();
    }

    public void hoverElement(WebDriver driver,String locator){
        action = new Actions(driver);
        action.moveToElement(getElementByXpath(driver,locator)).perform();
    }

    public void rightClickToElement(WebDriver driver,String locator){
        action = new Actions(driver);
        action.contextClick(getElementByXpath(driver,locator)).perform();
    }

    public void dragAndDrop(WebDriver driver,String sourceLocator, String targetLocator){
        action = new Actions(driver);
        action.dragAndDrop(getElementByXpath(driver,sourceLocator),getElementByXpath(driver,targetLocator));
    }

    public void sendKeyBoardToElement(WebDriver driver,String locator, Keys key){
        action = new Actions(driver);
        action.sendKeys(getElementByXpath(driver,locator),key).perform();
    }

    public Object executeForBrowser(WebDriver driver,String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver,String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver,String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver,String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElementByXpath(driver,locator);
        String originalStyle = getAttributeValue(driver,locator,"style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElementByXpath(driver,locator));
    }

    public void scrollToElement(WebDriver driver,String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElementByXpath(driver,locator));
    }

    public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElementByXpath(driver,locator));
    }

    public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElementByXpath(driver,locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElementByXpath(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElementByXpath(driver,locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver,String locator,String... params){
        explicitWait = new WebDriverWait(driver,GlobalConstants.TIME_OUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getXpath(String.format(locator, (Object[]) params))));
    }

    public void waitForElementClickable(WebDriver driver,String locator, String... params){
        explicitWait = new WebDriverWait(driver,GlobalConstants.TIME_OUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getXpath(String.format(locator,  (Object[]) params))));
    }

    public void waitForElementInvisible(WebDriver driver,String locator){
        explicitWait = new WebDriverWait(driver,GlobalConstants.TIME_OUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getXpath(locator)));
    }

    //Nop commerce upload file function
    public void uploadMultipleFiles(WebDriver driver, String panelName, String... fileNames){
        String filePath = System.getProperty("user.dir") + File.separator +"uploadFiles" + File.separator;
        String fullFileName = "";
        for (String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getElementByXpath(driver, ProductDetailUI.UPLOAD_FILE_BUTTON_BY_CARD_NAME,panelName).sendKeys(fullFileName);
    }
}


