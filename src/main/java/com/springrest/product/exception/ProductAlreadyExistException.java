package com.springrest.product.exception;

import lombok.Getter;

@Getter
public class ProductAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Integer code;

	public ProductAlreadyExistException(String message, Integer code) {
		super(message);
		this.code = code;
	}

}