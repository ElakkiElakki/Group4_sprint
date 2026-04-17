package pages;

import org.openqa.selenium.WebDriver;

public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultsDisplayed() {

        try { Thread.sleep(5000); } catch (Exception e) {}

        return driver.getCurrentUrl().contains("cars");
    }
}