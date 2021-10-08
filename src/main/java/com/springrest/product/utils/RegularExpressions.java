package com.springrest.product.utils;

public class RegularExpressions {

	private RegularExpressions() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SKU = "^FAL-[1-9][0-9]+$";
	public static final String URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
	public static final String PRICE = "^[1-9][0-9]*(\\.[0-9]{0,2})?$";
}
