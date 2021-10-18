package nopCommerce.pageObject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    WebDriver driver;
    private static LoginPO loginPO;
    private static DashboardPO dashboardPO;
    private static ProductSearchPO productSearchPO;
    private static ProductDetailPO productDetailPO;

    private PageGeneratorManager(){

    }

    public static LoginPO getLoginPO (WebDriver driver){
        if(loginPO == null){
            loginPO = new LoginPO(driver);
        }
        return  loginPO;
    }

    public static DashboardPO getDashboardPO (WebDriver driver){
        if(dashboardPO == null){
            dashboardPO = new DashboardPO(driver);
        }
        return  dashboardPO;
    }

    public static ProductSearchPO getProductSearchPO (WebDriver driver){
        if(productSearchPO == null){
            productSearchPO = new ProductSearchPO(driver);
        }
        return  productSearchPO;
    }

    public static ProductDetailPO getProductDetailPO (WebDriver driver){
        if(productDetailPO == null){
            productDetailPO = new ProductDetailPO(driver);
        }
        return  productDetailPO;
    }


}
