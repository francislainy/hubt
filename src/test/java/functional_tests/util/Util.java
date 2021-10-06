package functional_tests.util;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import com.github.dzieciou.testing.curl.Options;


import java.util.HashMap;

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


    public static <T> T createClassFromMap(HashMap map, Class<T> c) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, c);
    }
}
