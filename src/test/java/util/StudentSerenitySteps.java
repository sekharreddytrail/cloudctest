package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;


public class StudentSerenitySteps {

    public Response response;
    public String baseUrl = "https://devapi.currencycloud.com/v2";
    public String token = null;
    private DataGenerator dataGenerator = new DataGenerator();


    public void verifyThatResponseWasOk() {

        System.out.println(response.then().extract().statusCode() + "<<<<<<<<<<");
        assertThat(response.then().extract().statusCode()).isEqualTo(SC_OK);

    }


    public Response postdata() {

        SerenityRest.reset();

        return response = SerenityRest
                .given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .body(dataGenerator.dataforuser())
                .request()
                .log().all()
                .post("/authenticate/api");




    }


    public void extracttoken() {
        System.out.println(response.then().extract().jsonPath().get("auth_token").toString());

        token = response.then().extract().jsonPath().get("auth_token").toString();
    }


    public void verifyThatResponseWasCreated() {
        assertThat(response.then().extract().statusCode()).isEqualTo(SC_CREATED);
    }


    public Response generateQuote(Object selllingcurrency, Object buyingcurrency, Object type, Object amount) {
        SerenityRest.reset();

        return response = SerenityRest
                .given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON).
                        header("X-Auth-Token", token)
                .baseUri(baseUrl)
                .request()
                .log().all()
                .get("/rates/detailed?buy_currency=" + buyingcurrency + "&sell_currency=" + selllingcurrency + "&fixed_side=" + type + "&amount=" + amount + "&conversion_date_preference=earliest");

    }


    public void verifyQuote(double buyingamount) {
        System.out.println(response.asString() + "<<<<<<<<<<");
        String transferrate = (response.then().extract().body().jsonPath().get("client_rate").toString());
        double d = Double.parseDouble(transferrate);
        Double verifyamount = d * buyingamount;

        String client_buy_amount = response.then().extract().body().jsonPath().get("client_buy_amount").toString();
        double client_buy_amount_updated = Double.parseDouble(client_buy_amount.substring(0, client_buy_amount.length() - 1));
        assertThat(client_buy_amount_updated).isEqualTo(verifyamount);

    }

    public Response returnsErrorforQuote(Object selllingcurrency, Object buyingcurrency, Object type, Object amount) {
        SerenityRest.reset();

        return response = SerenityRest
                .given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON).
                        header("X-Auth-Token", token)
                .baseUri(baseUrl)
                .request()
                .log().all()
                .get("/rates/detailed?buy_currency=" + buyingcurrency + "&sell_currency=" + selllingcurrency + "&fixed_side=" + type + "&amount=" + amount + "&conversion_date_preference=earliest");

    }

    public void verifybodyContainsError() {

        assertThat(response.getBody().asString().contains("error_code"));
        assertThat(response.then().extract().statusCode()).isEqualTo(SC_BAD_REQUEST);
        SerenityRest.rest();
    }

}
