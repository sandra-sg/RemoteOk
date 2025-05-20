package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    public WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterKeywordInSearchBar(String keyword) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='your next job?']")));
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    public void selectFromSearchDropdown(String optionText) {
        By dropdownOption = By.xpath("//div[@data-slug='dev']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(dropdownOption));
        option.click();
    }

    // Locator for job titles in the result table
    public List<WebElement> jobTitles() {
        return driver.findElements(By.className("company_and_position"));

    }

    // Open the homepage URL
    public void open() {
        driver.get("https://remoteok.com");
    }
    // Count the jobs shown
    public int jobTitleCount() {
        return jobTitles().size();
    }
}






