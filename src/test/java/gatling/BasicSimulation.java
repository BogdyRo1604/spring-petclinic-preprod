package gatling;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8081");

    ScenarioBuilder scn =
            scenario("PetClinic Load Test")
                    .exec(http("home").get("/"))
                    .pause(1)
                    .exec(http("owners").get("/owners"))
                    .pause(1)
                    .exec(http("vets").get("/vets"));

    {
        setUp(
                scn.injectOpen(rampUsers(20).during(10))
        ).protocols(httpProtocol);
    }
}