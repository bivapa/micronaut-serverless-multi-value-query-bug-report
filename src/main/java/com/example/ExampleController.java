package com.example;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller
public class ExampleController {
    @Post("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<String> post(HttpRequest<?> request) {
        return HttpResponse.ok(request.getParameters().asMap().get("MULTI_PARAM").get(0));
    }
}
