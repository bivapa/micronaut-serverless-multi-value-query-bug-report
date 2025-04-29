package com.example;



import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class ExampleServerControllerTest {
    @Inject
    @Client("/")
    private HttpClient client;

    @Test
    void testMultiValueQueryParameter() {
        var response = client.toBlocking()
                            .exchange(HttpRequest.POST("/?MULTI_PARAM=foo&MULTI_PARAM=bar", null)
                            .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED));
        assertEquals("foo", response.getBody(String.class).get());
    }
}
