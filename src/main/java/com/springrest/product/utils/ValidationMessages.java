package com.springrest.product.utils;

public class ValidationMessages {

	private ValidationMessages() {
		throw new IllegalStateException("Utility class");
	}

	public static final String INVALID_FORMAT = "Invalid format";
	public static final String INVALID_RANGE = "Invalid range";
	public static final String INVALID_SIZE = "Invalid size";
	public static final String BLANK_FIELD = "This field is required";
	public static final String NULL_FIELD = "This field is required";

	
}
