package com.springrest.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.product.dto.ProductDTO;
import com.springrest.product.dto.ServiceResponseDTO;
import com.springrest.product.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {

		this.productService = productService;

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {

		ServiceResponseDTO<ProductDTO> response = productService.getProductById(id);

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProducts() {

		ServiceResponseDTO<List<ProductDTO>> response = productService.getAllProducts();

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
	}

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO body) {

		ServiceResponseDTO<ProductDTO> response = productService.addProduct(body);

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
	}

	@PutMapping(value = "/put", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> updateOrCreateProduct(@RequestBody @Valid ProductDTO body) {

		ServiceResponseDTO<ProductDTO> response = productService.updateOrCreateProduct(body);

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
	}

	@PatchMapping(value = "/patch", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO body) {
		
		ServiceResponseDTO<ProductDTO> response = productService.updateProduct(body);

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
		
	}

	@PutMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

		ServiceResponseDTO<Void> response = productService.deleteProduct(id);

		return ResponseEntity.status(response.getStatusCode()).body(response.getData());
		
	}

}
