package com.example;

import jakarta.inject.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Singleton
public class JsonValidator {

    public static enum ValidationResult{
        VALID, INVALID
    }

    public static class ValidatorResult {
        private final ValidationResult validationResult;
        private final Optional<String> errorMessageObject;
        private final Optional<String> errorMessageArray;

        public ValidatorResult(ValidationResult validationResult, Optional<String> errorMessageObject, Optional<String> errorMessageArray) {
            this.validationResult = validationResult;
            this.errorMessageObject = errorMessageObject;
            this.errorMessageArray = errorMessageArray;
        }

        public ValidationResult getValidationResult() {
            return validationResult;
        }

        public Optional<String> getErrorMessageObject() {
            return errorMessageObject;
        }

        public Optional<String> getErrorMessageArray() {
            return errorMessageArray;
        }
    }


    public ValidatorResult isValidJson(String jsonString){
        Optional<String> errorMessageObject = Optional.empty();
        Optional<String> errorMessageArray = Optional.empty();
        try {
            new JSONObject(jsonString);
        } catch (JSONException exObject) {
            errorMessageObject = Optional.of(exObject.getMessage());
            try {
                new JSONArray(jsonString);
            } catch (JSONException exArray) {
                errorMessageArray = Optional.of(exArray.getMessage());
                return new ValidatorResult(ValidationResult.INVALID,errorMessageObject, errorMessageArray);
            }
        }
        return new ValidatorResult(ValidationResult.VALID, errorMessageObject,errorMessageArray);
    }

}
