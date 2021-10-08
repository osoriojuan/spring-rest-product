package com.springrest.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private Integer statusCode;
	private String errorDetail;
	
}
