package com.springrest.product.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springrest.product.annotations.NullOrNotBlank;
import com.springrest.product.utils.RegularExpressions;
import com.springrest.product.utils.ValidationMessages;

import lombok.Data;

@Data
public class ProductDTO {

	@Pattern(regexp = RegularExpressions.SKU, message = ValidationMessages.INVALID_FORMAT) 
	@Size(min = 11, message = ValidationMessages.INVALID_RANGE) 
	@Size(max = 12, message = ValidationMessages.INVALID_RANGE)
	@NotNull(message = ValidationMessages.NULL_FIELD)
	private String sku;
	
	@NotBlank(message = ValidationMessages.BLANK_FIELD)
	@Size(min=3,max=50, message = ValidationMessages.INVALID_SIZE)
	private String name;
	
	@NotBlank(message = ValidationMessages.BLANK_FIELD)
	@Size(min=3,max=50, message = ValidationMessages.INVALID_SIZE)
	private String brand;
	
	@NullOrNotBlank(message = ValidationMessages.BLANK_FIELD)
	private String size;
	
	@Pattern(regexp = RegularExpressions.PRICE, message = ValidationMessages.INVALID_FORMAT)
	@DecimalMin(message = ValidationMessages.INVALID_RANGE, value = "1.00")
	@DecimalMax(message = ValidationMessages.INVALID_RANGE, value = "99999999.00")
	@Pattern(regexp = RegularExpressions.PRICE, message = ValidationMessages.INVALID_FORMAT)
	private String price;
	
	@NotBlank(message = ValidationMessages.BLANK_FIELD)
	@Pattern(regexp = RegularExpressions.URL, message = ValidationMessages.INVALID_FORMAT) 
	private String principalImage;
	
	@Pattern(regexp = RegularExpressions.URL, message = ValidationMessages.INVALID_FORMAT) 
	private String otherImages;
	
}
