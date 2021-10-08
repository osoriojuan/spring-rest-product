package com.springrest.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "PRODUCT")
@Data
public class ProductEntity {
	
	@Id
	private String sku;
	private String name;
	private String brand;
	private String size;
	private String price;
	private String principalImage;
	private String otherImages;
	
}
