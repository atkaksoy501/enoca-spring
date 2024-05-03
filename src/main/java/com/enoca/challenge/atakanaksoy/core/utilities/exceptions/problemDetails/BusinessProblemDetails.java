package com.enoca.challenge.atakanaksoy.core.utilities.exceptions.problemDetails;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(){
        setTitle("Business Rule Violation");
        setType("http://enoca.com/exceptions/business");
        setStatus("400");
    }
}
