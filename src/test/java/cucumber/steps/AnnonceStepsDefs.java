package cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

public class AnnonceStepsDefs {

    private static Response response;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("A user asks for the announcements list")
    public void aUserAsksForTheAnnouncementsList(){
        RequestSpecification request = RestAssured.given();
        response = request.get("/annonces");
    }

    @Then("He should have a success response")
    public void heShouldHaveASuccessResponse() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}
