package TestCases;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import Pages.HomePage;

import java.time.Duration;
import java.util.List;

public class FilterSteps {
    public static WebDriver driver;
    HomePage homePage;

    @Given("the user is on the RemoteOK homepage")
    public void the_user_is_on_the_RemoteOK_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @When("the user start typing {string} in the search bar")
    public void the_user_types_in_the_search_bar(String keyword) {
        homePage.enterKeywordInSearchBar(keyword);

    }

    @And("the user selects {string} from the dropdown")
    public void the_user_selects_Developer_from_the_dropdown(String option) {
        homePage.selectFromSearchDropdown(option);
    }


    @Then("only jobs related to {string} should be displayed")
    public void only_jobs_related_to_Developer_should_be_displayed(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for at least one job to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='company position company_and_position']//a[@class='preventLink']")));

        // Get all job listings
        List<WebElement> jobListings = homePage.jobTitles();

        System.out.println("Total jobs found: " + jobListings.size());

        SoftAssert softAssert = new SoftAssert();


        // Loop through each job listing element
        for (WebElement job : jobListings) {
            String jobTitle = job.findElement(By.tagName("h2")).getText();
            System.out.println("Job Title: " + jobTitle);

            // Check if the job title contains the keyword (case-insensitive)
            boolean containsKeyword = jobTitle.toLowerCase().contains(keyword.toLowerCase());

            // Print a message to the console based on the result
            if (containsKeyword) {
                System.out.println("✔ Job contains the keyword: " + keyword);
            } else {
                System.out.println("✘ Job does NOT contain the keyword: " + keyword);
            }

            // Perform a soft assertion to validate that the job title includes the keyword
            softAssert.assertTrue(
                    containsKeyword,
                    "FAILED: Job does not contain the keyword '" + keyword + "': " + jobTitle
            );
        }

       // At the end of the loop, assert all collected soft assertion results
        softAssert.assertAll();

        driver.quit();

    }

    }
