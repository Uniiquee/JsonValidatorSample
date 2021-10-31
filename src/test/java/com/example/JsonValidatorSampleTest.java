package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class JsonValidatorSampleTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    JsonValidator jsonValidator;

    @Test
    void testItWorks() {
        assertTrue(application.isRunning());
    }

    @Test
    void validateJsonSimpleString(){
        assertEquals(JsonValidator.ValidationResult.VALID,jsonValidator.isValidJson("{}").getValidationResult());
    }

    @Test
    void validateJsonMultipleValues(){
        assertEquals(JsonValidator.ValidationResult.VALID,jsonValidator.isValidJson("{\"testvalue\":\"test\", \"value\": 200}").getValidationResult());
    }

    @Test
    void validateJsonInvalid(){
        assertEquals(JsonValidator.ValidationResult.INVALID,jsonValidator.isValidJson("}[..,").getValidationResult());
    }

    @Test
    void validateJsonInvalidErrorMessage(){
        assertEquals("A JSONObject text must begin with '{' at 1 [character 2 line 1]",jsonValidator.isValidJson("}[..,").getErrorMessageObject().get());
    }

    @Test
    void validateJsonInvalidErrorMessageArray(){
        assertEquals("Expected a ',' or ']' at 1 [character 2 line 1]",jsonValidator.isValidJson("[").getErrorMessageArray().get());
    }
}
