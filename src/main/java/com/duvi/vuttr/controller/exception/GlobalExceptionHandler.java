package com.duvi.vuttr.controller.exception;


import com.duvi.vuttr.domain.ApiError;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler
    @Nullable
    public final ResponseEntity<ApiError> exceptionHandler(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        LOGGER.error("Handling exception " + ex.getClass().getSimpleName() + " due to " + ex.getMessage() + " uuuuund " + ex.getCause() + " JA, GENAU!");

        if (ex instanceof ToolNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ToolNotFoundException tnfe = (ToolNotFoundException) ex;

            return handleToolNotFound(tnfe, headers, status, request);
        }
        else if (ex instanceof ToolAlreadyExistsException) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            ToolAlreadyExistsException taee = (ToolAlreadyExistsException) ex;

            return handleToolAlreadyExists(taee, headers, status, request);
        } else if (ex instanceof UserNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;
            return handleUserNotFound(unfe, headers, status, request);
        }
        else if (ex instanceof UserAlreadyExistsException) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            UserAlreadyExistsException uaee = (UserAlreadyExistsException) ex;
            return handleUserAlreadyExists(uaee, headers, status, request);
        }

        else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unbekanntes Exception, schau mal!: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);

        }
    }

    public ResponseEntity<ApiError> handleToolNotFound(ToolNotFoundException tnfe, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError error = new ObjectError("Tool Not Found Error", tnfe.getMessage());
        List<ObjectError> errorList = Collections.singletonList(error);
        return handleExceptionInternal(tnfe, new ApiError(errorList), headers, status, request);
    }
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException unfe, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError error = new ObjectError("User Nicht Gefunden:", unfe.getMessage());
        List<ObjectError> errorList = Collections.singletonList(error);
        return handleExceptionInternal(unfe, new ApiError(errorList), headers, status, request);
    }


    public ResponseEntity<ApiError> handleToolAlreadyExists(ToolAlreadyExistsException taee, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError error = new ObjectError("Tool Not Found Error", taee.getMessage());
        List<ObjectError> errorList = Collections.singletonList(error);
        return handleExceptionInternal(taee, new ApiError(errorList), headers, status, request);
    }
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException uaee, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError error = new ObjectError("User Schon Existiert:", uaee.getMessage());
        List<ObjectError> errorList = Collections.singletonList(error);
        return handleExceptionInternal(uaee, new ApiError(errorList), headers, status, request);
    }


    public ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }



}
