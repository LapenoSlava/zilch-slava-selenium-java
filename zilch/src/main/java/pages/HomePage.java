package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;

public class HomePage extends BaseTest {

    public WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    By signInButton = By.className("nav__top-account-item");
    By fromPostcodeTextBox = By.xpath("//*[@datagtmattribute='input-from postcode']");
    By toPostcodeTextBox = By.xpath(     "//*[@datagtmattribute='input-to postcode']");
    By weightList = By.xpath("//*[@class='e-dropdown__option-text']");
    By weightListButton = By.xpath("(//*[@data-test-id='dropdown-selected-text-value'])[2]");
    By sendParcelButton = By.xpath("//*[@data-gtm-track='btn-get a price']");
    By authorisedUserLabel = By.className("nav__user-name");
    By cookiesPopupAcceptButton = By.id("onetrust-accept-btn-handler");
//    String weightListXPath = "(//*[@class='e-dropdown__option-text'])";

    By alertCloseButton = By.xpath("//*[@aria-label='Close dialog']");
    By feedbackAlertFrame = By.id("fsrInvite");

    public boolean isAlertPresent() {
        try {
            new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(feedbackAlertFrame));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage closeFeedbackAlertIfPresent() {
        if (isAlertPresent()) {
            driver.findElement(alertCloseButton).click();
        }
        return this;
    }

    public LoginPage navigateToLoginPage() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public HomePage waitForCookiesAlert() {
        new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(cookiesPopupAcceptButton));
        return this;
    }
    public HomePage acceptCookiesAlert() {
        driver.findElement(cookiesPopupAcceptButton).click();
        return this;
    }

    public HomePage setPostcodeTo(String postcode) {
        driver.findElement(toPostcodeTextBox).sendKeys(postcode);
        return this;
    }

    public HomePage setPostcodeFrom(String postcode) {
        driver.findElement(fromPostcodeTextBox).sendKeys(postcode);
        return this;
    }

    public HomePage openListOfWeights () {
        driver.findElement(weightListButton).click();
        return this;
    }

    public HomePage setWeight(String weightXpath) {
        driver.findElement(By.xpath(weightXpath)).click();
        return this;
    }

    public HomePage verifyPageAuthorised(String userName) {
        new WebDriverWait(driver, Duration.ofMillis(2000)).
                until(ExpectedConditions.elementToBeClickable(authorisedUserLabel));

        Assert.assertEquals(
                driver.findElement(authorisedUserLabel).getText(),
                "Hello, " + userName, // verify username
                "Missing or incorrect welcome message");
        return this;
    }

    public SendParcelPage navigateToSendParcelPage() {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(sendParcelButton));

        driver.findElement(sendParcelButton).click();
        return new SendParcelPage(driver);
    }
}
