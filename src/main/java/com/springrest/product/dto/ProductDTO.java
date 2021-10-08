package com.springrest.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private String sku;
	private String name;
	private String brand;
	private String size;
	private Float price;
	private String principalImage;
	private String otherImages;
	
}
