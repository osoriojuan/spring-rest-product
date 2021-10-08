package com.springrest.product.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilsTest {

	@Test
	void constantsTest() {
		assertEquals("The entered product does not exist", Constants.PRODUCT_NOT_EXIST);
		assertEquals("The entered product already exists", Constants.PRODUCT_ALREADY_EXIST);

		assertEquals("Success", Constants.SUCCESS);
		assertEquals("Created", Constants.CREATED);
		assertEquals("Updated", Constants.UPDATED);
		assertEquals("No content", Constants.NO_CONTENT);

		assertEquals(11, Constants.PRODUCT_MIN_SKU);
		assertEquals(12, Constants.PRODUCT_MAX_SKU);

		assertEquals("1.00", Constants.MIN_PRICE);
		assertEquals("99999999.00", Constants.MAX_PRICE);

		assertEquals(3, Constants.MIN_SIZE_NAME);
		assertEquals(50, Constants.MAX_SIZE_NAME);

		assertEquals(3, Constants.MIN_SIZE_BRAND);
		assertEquals(50, Constants.MAX_SIZE_BRAND);

		assertEquals(404, Constants.NOT_EXIST_CODE);
		assertEquals(400, Constants.BAD_REQUEST_CODE);
		assertEquals(500, Constants.INTERNAL_SERVER_ERROR_CODE);

		assertEquals(200, Constants.SUCCESS_CODE);
		assertEquals(201, Constants.CREATED_CODE);
		assertEquals(204, Constants.NO_CONTENT_CODE);

	}

	@Test
	void regularExpressionsTest() {
		assertEquals("^FAL-[1-9][0-9]+$", RegularExpressions.SKU);
		assertEquals(
				"https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)",
				RegularExpressions.URL);
		assertEquals("^[1-9][0-9]*(\\.[0-9]{0,2})?$", RegularExpressions.PRICE);
	}

	@Test
	void validationMessagesTest() {
		assertEquals("Invalid format", ValidationMessages.INVALID_FORMAT);
		assertEquals("Invalid range", ValidationMessages.INVALID_RANGE);
		assertEquals("Invalid size", ValidationMessages.INVALID_SIZE);
		assertEquals("This field is required", ValidationMessages.BLANK_FIELD);
		assertEquals("This field is required", ValidationMessages.NULL_FIELD);
	}

}
