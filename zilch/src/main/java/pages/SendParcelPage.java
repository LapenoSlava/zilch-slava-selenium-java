package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import utils.*;

import java.time.Duration;

public class SendParcelPage extends BaseTest {
    public WebDriver driver;
    public SendParcelPage(WebDriver driver) {
        this.driver = driver;
    }
    By enabledContinueButton = By.xpath("//*[@data-gtm-track='btn - Continue']");
    By disabledContinueButton = By.xpath("//*[@data-gtm-track='btn - Continue-disabled']");
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

    public SendParcelPage closeFeedbackAlertIfPresent() {
        if (isAlertPresent()) {
            driver.findElement(alertCloseButton).click();
        }
        return this;
    }

    private void verifyButtonSelected(String id) {
        Assert.assertEquals(
                driver.findElement(By.id(id)).getText(),
                "Selected",
                "Button IS NOT selected");
    }

    public SendParcelPage setParcelSize(String id) {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));

        driver.findElement(By.id(id)).click();
        verifyButtonSelected(id);
        return this;
    }

    public SendParcelPage setDeliverySpeed(String id) {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));

        driver.findElement(By.id(id)).click();
        verifyButtonSelected(id);
        return this;
    }

    public SendParcelPage setDropoffOrCollection(String id) {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));

        driver.findElement(By.id(id)).click();
        verifyButtonSelected(id);
        return this;
    }

    public SendParcelPage setDeliveryLocation(String id) {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));

        driver.findElement(By.id(id)).click();
        verifyButtonSelected(id);
        return this;
    }

    public SendParcelPage verifyNextSectionAppears(String section) {
        String xpath = "";
        switch(section) {
            case DeliveryOptions.Section.PARCEL_SIZE_TEXT:
                xpath =  DeliveryOptions.Section.PARCEL_SIZE_XPATH;
                break;
            case DeliveryOptions.Section.DELIVERY_SPEED_TEXT:
                xpath = DeliveryOptions.Section.DELIVERY_SPEED_XPATH;
                break;
            case DeliveryOptions.Section.DROP_OFF_OR_COLLECTION_TEXT:
                xpath =  DeliveryOptions.Section.DROP_OFF_OR_COLLECTION_XPATH;
                break;
            case DeliveryOptions.Section.PARCEL_DELIVERY_LOCATION_TEXT:
                xpath = DeliveryOptions.Section.PARCEL_DELIVERY_LOCATION_XPATH;
                break;
            default:
                System.out.println("No such section on Send A Parcel Page");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        Assert.assertEquals(
                driver.findElement(By.xpath(xpath)).getText(),
                section,
                "Incorrect Header of section or Section IS NOT opened");
        return this;
    }

    public SendParcelPage verifyContinueButtonInactive() {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.visibilityOfElementLocated(disabledContinueButton));

        Assert.assertFalse(driver.findElement(disabledContinueButton).isEnabled(),
                "'Continue' button IS active");
        return this;
    }

    public SendParcelPage verifyContinueButtonActive() {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.visibilityOfElementLocated(enabledContinueButton));

        Assert.assertTrue(driver.findElement(enabledContinueButton).isEnabled(),
                "'Continue' button IS NOT active");
        return this;
    }
}
