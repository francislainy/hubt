package functional_tests.util;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import com.github.dzieciou.testing.curl.Options;


import static io.restassured.RestAssured.given;

public class Util {

    public static RequestSpecification getRequestSpecification() {

        /* Enables printing request as curl under the terminal as per https://github.com/dzieciou/curl-logger */
        Options options = Options.builder()
                .printMultiliner()
                .updateCurl(curl -> curl
                        .removeHeader("Host")
                        .removeHeader("User-Agent")
                        .removeHeader("Connection")
                        .setCookieHeader("location"))
                .build();

        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options).objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));

        return given()
                .config(config)
                .contentType(ContentType.JSON)
                .contentType("application/json\r\n")
                .header("Accept", "application/json").and()
                .header("Content-Type", "application/json")
                .redirects().follow(false)
                .urlEncodingEnabled(false)
                .relaxedHTTPSValidation()
                .when()
                .log()
                .everything();
    }

}
