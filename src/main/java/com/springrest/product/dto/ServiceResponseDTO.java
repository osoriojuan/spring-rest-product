package com.springrest.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceResponseDTO<T> {

	private Integer statusCode;
	private String message;
	private T data;

}
