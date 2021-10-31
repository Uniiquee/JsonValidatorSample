package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest
public class ExampleControllerTest {


        @Inject
        @Client("/")
        HttpClient httpClient;

    @Test
    void testOk() {
        HttpResponse response = httpClient.toBlocking().exchange(HttpRequest.POST("/validate","{\"testvalue\":\"test\", \"value\": 200}"));
        assertEquals(HttpResponse.ok().code(),
                response.code());
    }

    @Test()
    void testErrorMessage() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            httpClient.toBlocking().retrieve(HttpRequest.POST("/validate","...."));
        });
        assertEquals(exception.getResponse().body(), "A JSONObject text must begin with '{' at 1 [character 2 line 1]\n" +
                "A JSONArray text must start with '[' at 1 [character 2 line 1]");
    }

}
