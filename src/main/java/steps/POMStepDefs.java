package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.pages.*;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Given;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.quantum.listerners.QuantumReportiumListener.logStepStart;
@QAFTestStepProvider
public class POMStepDefs extends WebDriverTestCase {
    private final String USERNAME = getBundle().getPropertyValue("username");
    private final String PASSWORD = getBundle().getPropertyValue("password");
    private final String UNIT = getBundle().getPropertyValue("unit");
    private final String OWNER_FIRSTNAME = getBundle().getPropertyValue("ownerFirstName");
    private final String OWNER_LASTNAME = getBundle().getPropertyValue("ownerLastName");
    private final String COMPANY = getBundle().getPropertyValue("company");
    private final String MONTH = getBundle().getPropertyValue("month");
    private final String DAY = getBundle().getPropertyValue("day");
    private final String YEAR = getBundle().getPropertyValue("year");
    private final String EMAIL = getBundle().getPropertyValue("email");
    private final String COUNTRY = getBundle().getPropertyValue("country");
    private final String CITY = getBundle().getPropertyValue("city");
    private final String STREET = getBundle().getPropertyValue("street");
    private final String STATE = getBundle().getPropertyValue("state");
    private final String ZIP = getBundle().getPropertyValue("zip");
    private final String PHONE_NUMBER = getBundle().getPropertyValue("phoneNumber");

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Given("^I launch real estate site$")
    public void beforeTest() {
        logStepStart("Open browser");
        getDriver().get(getBundle().getPropertyValue("env.baseurl"));

    }
    @Given("^I login to real estate site$")
    public void loginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.validateLoginPage();
        HomePage homePage = loginPage.login(USERNAME, PASSWORD);
        homePage.validateUserLoggedIn(USERNAME);
    }

    @Given("Add new rental owner$")
    public void addNewRentalOwnerTest(){
        HomePage homePage = new HomePage();
        RentalOwnersPage rentalOwner = homePage.navigateRentalOwnersPage();
        RentalOwnerDetailsPage rentalOwnerDetailsPage = rentalOwner.clickAddNew();
        rentalOwnerDetailsPage.addNewOwnerEntry(
                OWNER_FIRSTNAME,
                OWNER_LASTNAME,
                COMPANY,
                YEAR,
                MONTH,
                DAY,
                EMAIL,
                PHONE_NUMBER,
                COUNTRY,
                STATE,
                CITY,
                STREET,
                ZIP
        );
        rentalOwnerDetailsPage.validateID();
    }
}
