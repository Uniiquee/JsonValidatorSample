package com.example;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Controller("/validate")
public class JsonValidatorController {

    @Inject
    JsonValidator jsonValidator;
    @Post
    @Produces()
    public HttpResponse validate(@Body String json) {
        boolean isValid = jsonValidator.isValidJson(json);
        if (isValid) {
            return HttpResponse.ok();
        } else {
            return HttpResponse.badRequest();
        }
    }
}
