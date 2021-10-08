package com.springrest.product.dao;

import org.springframework.data.repository.CrudRepository;

import com.springrest.product.entity.ProductEntity;

public interface ProductDAO extends CrudRepository<ProductEntity, String> {

}
