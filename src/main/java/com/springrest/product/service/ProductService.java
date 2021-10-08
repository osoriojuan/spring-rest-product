package com.springrest.product.service;

import java.util.List;

import com.springrest.product.dto.ProductDTO;
import com.springrest.product.dto.ServiceResponseDTO;

public interface ProductService {

	public ServiceResponseDTO<ProductDTO> getProductById(String id);

	public ServiceResponseDTO<List<ProductDTO>> getAllProducts();

	public ServiceResponseDTO<ProductDTO> addProduct(ProductDTO product);

	public ServiceResponseDTO<ProductDTO> updateOrCreateProduct(ProductDTO product);

	public ServiceResponseDTO<ProductDTO> updateProduct(ProductDTO product);

	public ServiceResponseDTO<Void> deleteProduct(String id);

}