package com.springrest.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.product.dto.ProductDTO;
import com.springrest.product.dto.ServiceResponseDTO;
import com.springrest.product.exception.ProductAlreadyExistException;
import com.springrest.product.exception.ProductDoesNotExistException;
import com.springrest.product.service.ProductService;

@SpringBootTest
class ProductControllerTest {

	ProductController productController;

	JacksonTester<ProductDTO> jsonProduct;

	MockMvc mvc;

	@Mock
	ProductService productService;

	@BeforeEach
	public void init() {
		productController = new ProductController(productService);
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(productController).setControllerAdvice(new ProductControllerAdvice())
				.build();
	}

	@Test
	void getProductByIdTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-123", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<ProductDTO> responseBody = new ServiceResponseDTO<ProductDTO>(200, "success", dto);
		when(productService.getProductById("FAL-123")).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(get("/product/FAL-123").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertEquals(200, responseController.getStatus());
		
	}
	
	@Test
	void getAllProductsTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-12345678", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<List<ProductDTO>> responseBody = new ServiceResponseDTO<List<ProductDTO>>(200, "success", List.of(dto));
		when(productService.getAllProducts()).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(get("/product/all").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertEquals(200, responseController.getStatus());
		
	}
	
	@Test
	void addProductTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-88189850", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<ProductDTO> responseBody = new ServiceResponseDTO<ProductDTO>(201, "success", dto);
		when(productService.addProduct(dto)).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(post("/product/add").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonProduct.write(dto).getJson())).andReturn().getResponse();
		
		assertEquals(201, responseController.getStatus());
		
	}
	
	@Test
	void addProductButInvalidFieldTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-1234", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<ProductDTO> responseBody = new ServiceResponseDTO<ProductDTO>(201, "success", dto);
		when(productService.addProduct(dto)).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(post("/product/add").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonProduct.write(dto).getJson())).andReturn().getResponse();
		
		assertEquals(400, responseController.getStatus());
		
	}
	
	@Test
	void putProductTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-88189850", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<ProductDTO> responseBody = new ServiceResponseDTO<ProductDTO>(201, "success", dto);
		when(productService.updateOrCreateProduct(dto)).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(put("/product/put").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonProduct.write(dto).getJson())).andReturn().getResponse();
		
		assertEquals(201, responseController.getStatus());
		
	}
	
	@Test
	void patchProductTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-88189850", "Name", "Brand", "Size", "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		ServiceResponseDTO<ProductDTO> responseBody = new ServiceResponseDTO<ProductDTO>(201, "success", dto);
		when(productService.updateProduct(dto)).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(patch("/product/patch").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonProduct.write(dto).getJson())).andReturn().getResponse();
		
		assertEquals(201, responseController.getStatus());
		
	}
	
	@Test
	void deleteProductTest() throws IOException, Exception {

		ServiceResponseDTO<Void> responseBody = new ServiceResponseDTO<>(204, "success", null);
		when(productService.deleteProduct("FAL-88189850")).thenReturn(responseBody);

		MockHttpServletResponse responseController = mvc
				.perform(delete("/product/delete/FAL-88189850").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertEquals(204, responseController.getStatus());
		
	}
	
	@Test
	void addProductButAlreadyExistTest() throws IOException, Exception {

		ProductDTO dto = new ProductDTO("FAL-88189850", "Name", "Brand", null, "1000.00", "http://image.com/image1",
				"http://image.com/image2");

		when(productService.addProduct(dto)).thenThrow(new ProductAlreadyExistException("ProductAlreadyExist", 400));

		MockHttpServletResponse responseController = mvc
				.perform(post("/product/add").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonProduct.write(dto).getJson())).andReturn().getResponse();
		
		assertEquals(400, responseController.getStatus());
		
	}
	
	@Test
	void deleteProductButNotExistTest() throws IOException, Exception {

		when(productService.deleteProduct("FAL-88189850")).thenThrow(new ProductDoesNotExistException("ProductNotExist", 404));

		MockHttpServletResponse responseController = mvc
				.perform(delete("/product/delete/FAL-88189850").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertEquals(404, responseController.getStatus());
		
	}
	
	@Test
	void deleteProductButRuntimeExceptionTest() throws IOException, Exception {

		when(productService.deleteProduct("FAL-88189850")).thenThrow(new RuntimeException());

		MockHttpServletResponse responseController = mvc
				.perform(delete("/product/delete/FAL-88189850").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertEquals(500, responseController.getStatus());
		
	}

}
