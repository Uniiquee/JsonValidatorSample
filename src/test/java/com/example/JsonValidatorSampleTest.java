package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(jsonValidator.isValidJson("{}"));
    }

    @Test
    void validateJsonMultipleValues(){
        assertTrue(jsonValidator.isValidJson("{\"testvalue\":\"test\", \"value\": 200}"));
    }

    @Test
    void validateJsonInvalid(){
        assertFalse(jsonValidator.isValidJson("}[..,"));
    }
}
