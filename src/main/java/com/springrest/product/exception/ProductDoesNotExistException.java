package com.springrest.product.exception;

import lombok.Getter;

@Getter
public class ProductDoesNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Integer code;

	public ProductDoesNotExistException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public ProductDoesNotExistException(String message, Integer code, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

}
