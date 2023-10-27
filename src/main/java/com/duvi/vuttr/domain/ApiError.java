package com.duvi.vuttr.domain;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private List<ObjectError> errors;

    public ApiError(List<ObjectError> errors ) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
