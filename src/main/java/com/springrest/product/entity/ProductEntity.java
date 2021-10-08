package com.springrest.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
