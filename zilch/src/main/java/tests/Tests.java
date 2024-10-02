package tests;
import org.testng.annotations.Test;
import pages.*;
import utils.*;

public class Tests extends BaseTest {
    @Test
    public void verifyEmptyLoginErrorMessages() {
        driver.get("https://evri.com/");
        new HomePage(driver)
                .waitForCookiesAlert()
                .acceptCookiesAlert()
                .navigateToLoginPage()
                .clickLoginButton()
                .verifyEmptyFieldsErrors();
    }

    @Test
    public void verifyAuthorisedPage() {
        User user = new User();

        driver.get("https://evri.com/");
        new HomePage(driver)
                .waitForCookiesAlert()
                .acceptCookiesAlert()
                .navigateToLoginPage()
                .loginWithCorrectCredentials(user.email(), user.password())
                .verifyPageAuthorised(user.userName());
    }

    @Test
    public void verifyParcelFlow() {
        User user = new User();

        String postcodeFrom = "BD11 1NE";
        String postcodeTo = "BD11 1ND";

        driver.get("https://evri.com/");
        new HomePage(driver)
                .waitForCookiesAlert()
                .acceptCookiesAlert()
                .navigateToLoginPage()
                .loginWithCorrectCredentials(user.email(), user.password())
                .closeFeedbackAlertIfPresent()
                .verifyPageAuthorised(user.userName())
                .setPostcodeFrom(postcodeFrom)
                .setPostcodeTo(postcodeTo)
                .openListOfWeights()
                .setWeight(WeightLimit.UNDER_1KG)
                .navigateToSendParcelPage()
                .closeFeedbackAlertIfPresent()
                .setParcelSize(DeliveryOptions.ParcelSize.POSTABLE_OR_LETTER)
                .verifyNextSectionAppears(DeliveryOptions.Section.DELIVERY_SPEED_TEXT)
                .setDeliverySpeed(DeliveryOptions.DeliverySpeed.STANDARD)
                .verifyNextSectionAppears(DeliveryOptions.Section.DROP_OFF_OR_COLLECTION_TEXT)
                .setDropoffOrCollection(DeliveryOptions.DropOffOrCollection.PARCELSHOP_OR_LOCKER)
                .verifyContinueButtonInactive()
                .setDeliveryLocation(DeliveryOptions.ParcelDeliveryLocation.TO_PARCELSHOP)
                .verifyContinueButtonActive();
    }
}