package com.geronimoapps.el_carril_del_sabor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Catch the exception type ResourceNotFoundException and respond with HTTP 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFound(ResourceNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(AdministratorDoesNotPermissionsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleAdministratorDoesNotPermissions(AdministratorDoesNotPermissionsException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(CustomerDoesNotPermissionsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleCustomerDoesNotPermissions(CustomerDoesNotPermissionsException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(UserIsNotACustomer.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserIsNotACustomer(UserIsNotACustomer ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(UserIsNotAnAdministrator.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserIsNotAnAdministrator(UserIsNotAnAdministrator ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(UserIsNotADelivery.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserIsNotADelivery(UserIsNotADelivery ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(AttributeNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleAttributeNotValid(AttributeNotValidException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return errors;
    }

    @ExceptionHandler(ProductTypeNotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleProductTypeNotAvailable(ProductTypeNotAvailable ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(StatusOrderNotValid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleStatusOrderNotValid(StatusOrderNotValid ex) {
        return Map.of("error", ex.getMessage());
    }
}
