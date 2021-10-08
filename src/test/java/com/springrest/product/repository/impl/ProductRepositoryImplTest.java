package com.springrest.product.repository.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springrest.product.dao.ProductDAO;
import com.springrest.product.entity.ProductEntity;

@SpringBootTest
class ProductRepositoryImplTest {

	ProductRepositoryImpl productRepositoryImpl;

	@Mock
	ProductDAO productDAO;

	@BeforeEach
	public void init() {
		productRepositoryImpl = new ProductRepositoryImpl(productDAO);
	}

	@Test
	void getProductByIdTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		when(productDAO.findById(id)).thenReturn(Optional.of(entity));
		ProductEntity response = productRepositoryImpl.getProductById(id);

		assertEquals("FAL-123", response.getSku());
	}

	@Test
	void getProductByIdNotExistTest() {
		String id = "FAL-123";

		when(productDAO.findById(id)).thenReturn(Optional.empty());
		ProductEntity response = productRepositoryImpl.getProductById(id);

		assertEquals(null, response);
	}

	@Test
	void getAllProductsTest() {

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		when(productDAO.findAll()).thenReturn(List.of(entity));
		List<ProductEntity> response = productRepositoryImpl.getAllProducts();

		assertEquals("FAL-123", response.get(0).getSku());
	}

	@Test
	void getAllProductsEmptyTest() {
		when(productDAO.findAll()).thenReturn(List.of());
		List<ProductEntity> response = productRepositoryImpl.getAllProducts();

		assertEquals(0, response.size());
	}

	@Test
	void deleteProductTest() {

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");
		
		assertAll(() ->{productRepositoryImpl.deleteProduct(entity);});

	}
	
	@Test
	void createOrUpdateProductTest() {
		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		when(productDAO.save(entity)).thenReturn(entity);
		ProductEntity response = productRepositoryImpl.createOrUpdateProduct(entity);

		assertEquals("FAL-123", response.getSku());
	}
	
}
