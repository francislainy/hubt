package functional_tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static functional_tests.util.Util.getRequestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

// mvn -Dtest=functional_tests.*Test test

public class HubspotTest {

    @Test
    public void getResponseFromApi() {

//        11th 018032260 12h

        RequestSpecification rq = getRequestSpecification();
        Response respShowcase = rq.get("https://ed-showcase-service.staging0.hmheng-content-pipeline.br.internal/" + "ids/v1/districts/" + "ab20281e-f8ea-43e4-bc34-24f968c47c5b" + "/schools");

        assertEquals(401, respShowcase.getStatusCode());
    }

}
