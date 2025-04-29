package com.example;



import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import io.micronaut.context.ApplicationContext;
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction;
import io.micronaut.http.MediaType;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class ExampleServerlessControllerTest {
    @Inject
    private ApplicationContext applicationContext;

    private ApiGatewayProxyRequestEventFunction requestHandler;

    @BeforeEach
    void init() {
        requestHandler = new ApiGatewayProxyRequestEventFunction(this.applicationContext);
    }

    @Test
    void testMultiValueQueryParameter() {
        var requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("POST");
        requestEvent.setPath("/");
        requestEvent.setMultiValueQueryStringParameters(Map.of("MULTI_PARAM", List.of("foo", "bar")));
        requestEvent.setHeaders(Map.of("Content-Type", MediaType.APPLICATION_FORM_URLENCODED));
        requestEvent.setIsBase64Encoded(false);

        var responseEvent = requestHandler.handleRequest(requestEvent, null);

        assertEquals("foo", responseEvent.getBody());
    }
}
