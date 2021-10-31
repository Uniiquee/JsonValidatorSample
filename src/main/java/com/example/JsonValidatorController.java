package com.example;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import javax.ws.rs.Produces;

@Controller("/validate")
public class JsonValidatorController {

    @Inject
    JsonValidator jsonValidator;
    @Post
    @Produces()
    public HttpResponse validate(@Body String json) {
        JsonValidator.ValidatorResult result = jsonValidator.isValidJson(json);
        if (JsonValidator.ValidationResult.VALID.equals(result.getValidationResult())) {
            return HttpResponse.ok();
        } else {
            return HttpResponse.badRequest();
        }
    }
}
