package nopCommerce.pageUI;

public class ProductDetailUI {

    public static final String ADD_PRODUCT_PICTURE_BUTTON = "//button[text()='Add product picture']";
    public static final String TOGGLE_ICON_BY_CARD_NAME = "//div[@class='card-title' and contains(string(),'%s')]/following-sibling::div//i";
    public static final String UPLOAD_FILE_BUTTON_BY_CARD_NAME = "//div[@id='product-%s']//input[@type='file']";
    public static final String PRODUCT_PICTURE_BLOCK_BY_FILE_NAME = "//div[@class='upload-picture-block']//img[contains(@src,'%s')]";
    public static final String ALT_TEXTBOX_ADD_NEW = "//input[@id='AddPictureModel_OverrideAltAttribute']";
    public static final String TITLE_TEXTBOX_ADD_NEW = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
    public static final String DISPLAY_ORDER_TEXTBOX_ADD_NEW = "//input[@id='AddPictureModel_DisplayOrder']/preceding-sibling::input";
    public static final String EMPTY_TABLE_BY_CARD_NAME = "//table[@id='productpictures-grid']//td[text()='No data available in table']";
    public static final String INCREASE_VALUE_BOX_BY_PANEL_NAME = "//div[@data-card-name='product-%s']//span[@title='Increase value']";
    public static final String SAVE_BUTTON = "//button[@name='save']";
}
