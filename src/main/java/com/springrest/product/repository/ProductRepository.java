package com.springrest.product.repository;

import java.util.List;

import com.springrest.product.entity.ProductEntity;

public interface ProductRepository {
	
	public ProductEntity getProductById(String id);

	public List<ProductEntity> getAllProducts();

	public ProductEntity createOrUpdateProduct(ProductEntity product);

	public void deleteProduct(ProductEntity product);

}
