package com.springrest.product.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springrest.product.dao.ProductDAO;
import com.springrest.product.entity.ProductEntity;
import com.springrest.product.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductDAO productDAO;

	@Autowired
	public ProductRepositoryImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public ProductEntity getProductById(String id) {
		Optional<ProductEntity> response = productDAO.findById(id);
		return response.isPresent() ? response.get() : null;
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		Iterable<ProductEntity> iterableProduct = productDAO.findAll();
		return StreamSupport.stream(iterableProduct.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public void deleteProduct(ProductEntity product) {
		productDAO.delete(product);
	}

	@Override
	public ProductEntity createOrUpdateProduct(ProductEntity product) {
		return productDAO.save(product);
	}

}
