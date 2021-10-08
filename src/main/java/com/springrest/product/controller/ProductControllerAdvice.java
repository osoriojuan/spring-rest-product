package com.springrest.product.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.springrest.product.dto.ErrorResponse;
import com.springrest.product.exception.ProductAlreadyExistException;
import com.springrest.product.exception.ProductDoesNotExistException;
import com.springrest.product.utils.Constants;

@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerAdvice {

	@ResponseBody
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ErrorResponse handleProductAlreadyExistException(ProductAlreadyExistException ex) {
		return buildErrorResponse(ex.getMessage(),ex.getCode());
	}

	@ResponseBody
	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(ProductDoesNotExistException.class)
	public ErrorResponse handleProductDoesNotExistException(ProductDoesNotExistException ex) {
		return buildErrorResponse(ex.getMessage(),ex.getCode());
	}
	
	@ResponseBody
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public ErrorResponse handleGeneralRuntimeException(RuntimeException ex) {
		return buildErrorResponse(ex.getMessage(),null);
	}
	
	@ResponseStatus(BAD_REQUEST)
    @ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, WebRequest request) {

    	BindingResult result = ex.getBindingResult();
    	String message = " | Error when validating the entered fields : ";

        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
        	message = message
        	.concat(fieldError.getField())
        	.concat("=")
        	.concat(fieldError.getDefaultMessage())
        	.concat(" | ");
        }
    	
		return buildErrorResponse(message,Constants.BAD_REQUEST_CODE);
	}
	
	private ErrorResponse buildErrorResponse(String message, Integer code) {
		
		Integer statusCode = Constants.INTERNAL_SERVER_ERROR_CODE;
		String statusMessage = "Unknown error";
		if(null!=code) {
			statusCode=code;
		}
		
		if(null!=message) {
			statusMessage = message;
		}
		
		return new ErrorResponse(statusCode,statusMessage); 
	}
	
}
