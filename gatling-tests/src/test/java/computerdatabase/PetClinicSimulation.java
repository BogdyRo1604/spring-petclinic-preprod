package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class PetClinicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8081")
        .acceptHeader("text/html");

    ScenarioBuilder scn = scenario("PetClinic Test")
        .exec(http("Page accueil").get("/").check(status().is(200)))
        .pause(1)
        .exec(http("Page vets").get("/vets.html").check(status().is(200)))
        .pause(1);

    {
        setUp(
            scn.injectOpen(rampUsers(20).during(30))
        ).protocols(httpProtocol);
    }
}