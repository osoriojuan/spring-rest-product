package com.springrest.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.springrest.product.dto.ProductDTO;
import com.springrest.product.dto.ServiceResponseDTO;
import com.springrest.product.entity.ProductEntity;
import com.springrest.product.exception.ProductAlreadyExistException;
import com.springrest.product.exception.ProductDoesNotExistException;
import com.springrest.product.repository.ProductRepository;

@SpringBootTest
class ProductServiceImplTest {

	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	@Mock
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		productServiceImpl = new ProductServiceImpl(productRepository, modelMapper);
	}

	@Test
	void getProductByIdTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(entity);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);

		ServiceResponseDTO<ProductDTO> response = productServiceImpl.getProductById(id);

		assertEquals(200, response.getStatusCode());
		assertEquals("FAL-123", response.getData().getSku());
	}

	@Test
	void getProductByIdNotExistTest() {
		String id = "FAL-123";

		when(productRepository.getProductById(id)).thenReturn(null);

		Assertions.assertThrows(ProductDoesNotExistException.class, () -> {
			productServiceImpl.getProductById(id);
		});
	}

	@Test
	void getAllProductsTest() {

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");
		
		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");
		
		List<ProductEntity> entities = List.of(entity);

		when(productRepository.getAllProducts()).thenReturn(entities);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);

		ServiceResponseDTO<List<ProductDTO>> response = productServiceImpl.getAllProducts();

		assertEquals(200, response.getStatusCode());
		assertEquals("FAL-123", response.getData().get(0).getSku());
	}
	
	@Test
	void getAllProductsEmptyListTest() {
		
		List<ProductEntity> entities = List.of();

		when(productRepository.getAllProducts()).thenReturn(entities);

		ServiceResponseDTO<List<ProductDTO>> response = productServiceImpl.getAllProducts();

		assertEquals(204, response.getStatusCode());
	}
	
	@Test
	void addProductTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(null);
		when(productRepository.createOrUpdateProduct(entity)).thenReturn(entity);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);
		when(modelMapper.map(dto, ProductEntity.class)).thenReturn(entity);

		ServiceResponseDTO<ProductDTO> responseAdd = productServiceImpl.addProduct(dto);

		assertEquals(201, responseAdd.getStatusCode());
		assertEquals("Created", responseAdd.getMessage());
	}
	
	@Test
	void addProductButAlreadyExistTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(entity);

		Assertions.assertThrows(ProductAlreadyExistException.class, () -> {
			productServiceImpl.addProduct(dto);
		});
	}
	
	@Test
	void addUpdateOrCreateProductTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(null);
		when(productRepository.createOrUpdateProduct(entity)).thenReturn(entity);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);
		when(modelMapper.map(dto, ProductEntity.class)).thenReturn(entity);
		
		ServiceResponseDTO<ProductDTO> responseAddOrUpdate = productServiceImpl.updateOrCreateProduct(dto);

		assertEquals(201, responseAddOrUpdate.getStatusCode());
		assertEquals("Created", responseAddOrUpdate.getMessage());

	}
	
	@Test
	void updateProductTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(entity);
		when(productRepository.createOrUpdateProduct(entity)).thenReturn(entity);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);
		when(modelMapper.map(dto, ProductEntity.class)).thenReturn(entity);

		ServiceResponseDTO<ProductDTO> responseUpdate = productServiceImpl.updateProduct(dto);

		assertEquals(201, responseUpdate.getStatusCode());
		assertEquals("Created", responseUpdate.getMessage());
	}
	
	@Test
	void updateProductButNotExistTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productRepository.getProductById(id)).thenReturn(null);
		when(productRepository.createOrUpdateProduct(entity)).thenReturn(null);

		Assertions.assertThrows(ProductDoesNotExistException.class, () -> {
			productServiceImpl.updateProduct(dto);
		});
	}
	
	@Test
	void deleteProductTest() {
		String id = "FAL-123";

		ProductEntity entity = new ProductEntity("FAL-123", "Name", "Brand", "Size", "1000.00",
				"http://image.com/image1", "http://image.com/image2");

		ProductDTO dto = new ProductDTO();

		when(productRepository.getProductById(id)).thenReturn(entity);
		when(modelMapper.map(entity, ProductDTO.class)).thenReturn(dto);

		ServiceResponseDTO<Void> response = productServiceImpl.deleteProduct(id);
		
		assertEquals(204, response.getStatusCode());

	}
	
	@Test
	void deleteProductButNotExistTest() {
		String id = "FAL-123";
		
		when(productRepository.getProductById(id)).thenReturn(null);
		
		Assertions.assertThrows(ProductDoesNotExistException.class, () -> {
			productServiceImpl.deleteProduct(id);
		});

	}

	
}
