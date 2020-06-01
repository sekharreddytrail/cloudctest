package details;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import util.StudentSerenitySteps;

import java.util.List;

public class StepsForFeatures {


    @Steps
    StudentSerenitySteps step;

//generate token

    @Given("^i try to autheticate user$")
    public void iTryToAutheticateUser() {
        step.postdata();
        step.extracttoken();
        step.verifyThatResponseWasOk();
    }

    // create quote


    @When("^i try to create quote for gbp and usd$")
    public void iTryToCreateQuoteForGbpAndUsd(DataTable dt) {
        List<Object> list = dt.asList(Object.class);
        step.generateQuote(list.get(0), list.get(1), list.get(2), list.get(3));

    }
// verify amount

    @Then("^I verify buying amount is correct$")
    public void iVerifyBuyingAmountIsCorrect(DataTable dt) {
        List<Double> list = dt.asList(double.class);
        step.verifyQuote(list.get(0));
        step.verifyThatResponseWasOk();
    }

// verify error message

    @Then("^I verify it doesnot show value$")
    public void iVerifyItDoesnotShowValue() {

        step.verifybodyContainsError();

    }
}
