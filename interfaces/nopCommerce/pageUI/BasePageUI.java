package nopCommerce.pageUI;

public class BasePageUI {
    public static final String PRODUCT_UPDATE_SUCCESS_MESSAGE = "//div[@class='alert alert-success alert-dismissable' and contains(string(),'The product has been updated successfully.')]";
    public static final String MENU_ITEMS = "//ul[@role='menu']//p[contains(text(),'%s')]/parent::a/parent::li[@class='nav-item has-treeview']";
    public static final String SUB_MENU_ITEMS = "//ul[@class='nav nav-treeview' and @style]//p[contains(text(),'%s')]";
}
