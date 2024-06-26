package com.enoca.challenge.atakanaksoy.core.utilities.exceptions.problemDetails;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails{
    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("http://enoca.com/exceptions/validation");
        setStatus("400");
    }
    private Map<String,String> errors;
}
