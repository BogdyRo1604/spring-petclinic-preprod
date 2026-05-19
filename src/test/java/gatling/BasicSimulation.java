package gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8081")
            .acceptHeader("text/html");

    ScenarioBuilder scn =
        scenario("PetClinic Preprod Load Test")
            .exec(http("Home").get("/"))
            .pause(1)
            .exec(http("Owners").get("/owners"))
            .pause(1)
            .exec(http("Vets").get("/vets"));

    {
        setUp(
            scn.injectOpen(rampUsers(50).during(20))
        ).protocols(httpProtocol);
    }
}