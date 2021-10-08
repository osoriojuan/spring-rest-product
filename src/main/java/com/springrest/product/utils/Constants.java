package com.springrest.product.utils;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	// error messages
	public static final String PRODUCT_NOT_EXIST = "The entered product does not exist";
	public static final String PRODUCT_ALREADY_EXIST = "The entered product already exists";

	// success messages
	public static final String SUCCESS = "Success";
	public static final String CREATED = "Created";
	public static final String UPDATED = "Updated";
	public static final String NO_CONTENT = "No content";
	
	//ProductValidations
	public static final int PRODUCT_MIN_SKU = 11;
	public static final int PRODUCT_MAX_SKU = 12;
	
	public static final String MIN_PRICE = "1.00";
	public static final String MAX_PRICE = "99999999.00";
	
	public static final int MIN_SIZE_NAME = 3;
	public static final int MAX_SIZE_NAME = 50;
			
	public static final int MIN_SIZE_BRAND = 3;
	public static final int MAX_SIZE_BRAND = 50;
	
	// error codes
	public static final Integer NOT_EXIST_CODE = 404;
	public static final Integer BAD_REQUEST_CODE = 400;
	public static final Integer INTERNAL_SERVER_ERROR_CODE = 500;

	// success codes
	public static final Integer SUCCESS_CODE = 200;
	public static final Integer CREATED_CODE = 201;
	public static final Integer NO_CONTENT_CODE = 204;

}
