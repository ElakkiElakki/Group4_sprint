package utils;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.TaxiPage;

public class Pages {

    public static WebDriver driver;
    public static LoginPage LP;
    public static TaxiPage TP;

    public static void initPages() {
        LP = new LoginPage(driver);
        TP = new TaxiPage(driver);
    }
}