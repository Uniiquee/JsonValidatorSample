package com.example;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import javax.ws.rs.Produces;
import java.util.Optional;

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
            String httpBody = "";
            httpBody = getErrorMessageWithTrailingLineBreak(result.getErrorMessageObject());
            httpBody = httpBody + getErrorMessageWithTrailingLineBreak(result.getErrorMessageArray()).trim();
            return HttpResponse.badRequest(httpBody);
        }
    }

    private String getErrorMessageWithTrailingLineBreak(Optional<String> errorMessage) {
        return errorMessage.map(errorMessageText -> errorMessageText + "\n").orElse("");
    }
}
