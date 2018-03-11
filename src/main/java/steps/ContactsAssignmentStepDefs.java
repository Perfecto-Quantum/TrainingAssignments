
package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.QuantumCourseUtils;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
@QAFTestStepProvider
public class ContactsAssignmentStepDefs extends QuantumCourseUtils {


    @When("I Delete Contact \"([^\"]*)\"$")
    public void I_delete_contact(String searchKey) throws Throwable {

        System.out.println("deleting contact:" + searchKey);

        //the flow of iOS and android is different, so this step has an implementation per platform.
        if(isAndroid()) {
            getDriver().findElement("contact.open").click();
            getDriver().findElement("more").click();
            getDriver().findElement("delete").click();
            getDriver().findElement("delete.confirm").click();

        }else {
            //in this case the name of the contact is inside the locator. So we will build the locator programatically instead of useing the locator file
            String path = "//*[@label=\"Search results\"]//*[@value=\"" + searchKey + "\"]";
            System.out.println("xpath is: " + path);
            getDriver().findElementByXPath(path).click();
            getDriver().findElement("edit.contact").click();
            Thread.sleep(4000);
            //to get to the delete button, we need to scroll to it. we will use a visual checkpoint command to scroll.
            //there is no pre-defined step for a visual scroll so we will run the Perfecto command from the code.
            Map<String, Object> params = new HashMap<>();
            params.put("content", "Delete");
            params.put("scrolling", "scroll");
            getDriver().executeScript("mobile:checkpoint:text", params);
            Thread.sleep(3000);
            getDriver().findElement("delete.contact");
        }


    }

    @When("Delete Contact \"([^\"]*)\" if exists$")
    public void check_contact_exist_delete(String searchKey) throws Throwable {

        DeviceUtils.switchToContext("NATIVE_APP");
        getDriver().findElement("search.contact").sendKeys(searchKey);
        //trying to look for the no contacts found object. if it is NOT found when we will assume the contact exists and delete
        try {
            getDriver().findElement("no.contacts").getText();
        }catch(Exception e) {
            I_delete_contact(searchKey);//The  catch is executed when the "no contacs found is not present, so we assume the contact exists.
        }


    }
}
