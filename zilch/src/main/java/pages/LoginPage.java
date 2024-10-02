package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;

public class LoginPage extends BaseTest {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    By emailTextBox = By.id("1-email");
    By passwordTextBox = By.name("password");
    By loginButton = By.className("auth0-lock-submit");
    By emptyEmailErrorMessage = By.id("auth0-lock-error-msg-email");
    By emptyPasswordErrorMessage = By.id("auth0-lock-error-msg-password");

    public HomePage loginWithCorrectCredentials(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBox));
        driver.findElement(emailTextBox).sendKeys(email);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

    public LoginPage clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
        return this;
    }

    public LoginPage verifyEmptyFieldsErrors() {
        new WebDriverWait(driver, Duration.ofMillis(2000))
                .until(ExpectedConditions.elementToBeClickable(emptyEmailErrorMessage));

        Assert.assertEquals(
                driver.findElement(emptyEmailErrorMessage).getText(),
                "Email can't be blank",
                "Missing or incorrect EMAIL error message");

        Assert.assertEquals(driver.findElement(
                emptyPasswordErrorMessage).getText(),
                "Password can't be blank",
                "Missing or incorrect PASSWORD error message");
        return this;
    }
}
