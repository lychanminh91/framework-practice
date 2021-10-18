package dataGrid.pageUI;

public class HomePageUI {
    public static final String PAGE_NUMBER = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
    public static final String PAGE_NUMBER_ACTIVATED = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_FIELDS = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
}
