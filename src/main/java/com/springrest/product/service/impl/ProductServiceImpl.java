package com.springrest.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.product.dto.ProductDTO;
import com.springrest.product.dto.ServiceResponseDTO;
import com.springrest.product.entity.ProductEntity;
import com.springrest.product.exception.ProductAlreadyExistException;
import com.springrest.product.exception.ProductDoesNotExistException;
import com.springrest.product.repository.ProductRepository;
import com.springrest.product.service.ProductService;
import com.springrest.product.utils.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ServiceResponseDTO<ProductDTO> getProductById(String id) {

		ProductEntity entity = productRepository.getProductById(id);

		if (null == entity) {
			throw new ProductDoesNotExistException(Constants.PRODUCT_NOT_EXIST, Constants.BAD_REQUEST_CODE);
		}

		ProductDTO dto = modelMapper.map(entity, ProductDTO.class);

		String message = Constants.SUCCESS;
		Integer code = Constants.SUCCESS_CODE;

		return new ServiceResponseDTO<>(code, message, dto);
	}

	@Override
	public ServiceResponseDTO<List<ProductDTO>> getAllProducts() {

		List<ProductEntity> entityList = productRepository.getAllProducts();
		String message = Constants.SUCCESS;
		Integer code = Constants.SUCCESS_CODE;
		if (entityList.isEmpty()) {
			code = Constants.NO_CONTENT_CODE;
			message = Constants.NO_CONTENT;
			return new ServiceResponseDTO<>(code, message, new ArrayList<>());
		}
		
		List<ProductDTO> dtoList = entityList.stream().map(dto -> modelMapper.map(dto, ProductDTO.class))
				.collect(Collectors.toList());

		return new ServiceResponseDTO<>(code, message, dtoList);
	}

	@Override
	public ServiceResponseDTO<ProductDTO> addProduct(ProductDTO product) {

		if (null != productRepository.getProductById(product.getSku())) {
			throw new ProductAlreadyExistException(Constants.PRODUCT_ALREADY_EXIST, Constants.BAD_REQUEST_CODE);
		}

		ProductDTO dtoResponse = commonCreateOrUpdateOperation(product);

		return new ServiceResponseDTO<>(Constants.CREATED_CODE, Constants.CREATED, dtoResponse);
	}

	@Override
	public ServiceResponseDTO<ProductDTO> updateOrCreateProduct(ProductDTO product) {

		ProductDTO dtoResponse = commonCreateOrUpdateOperation(product);

		return new ServiceResponseDTO<>(Constants.CREATED_CODE, Constants.CREATED, dtoResponse);

	}

	@Override
	public ServiceResponseDTO<ProductDTO> updateProduct(ProductDTO product) {

		if (null == productRepository.getProductById(product.getSku())) {
			throw new ProductDoesNotExistException(Constants.PRODUCT_NOT_EXIST, Constants.BAD_REQUEST_CODE);
		}

		ProductDTO dtoResponse = commonCreateOrUpdateOperation(product);

		return new ServiceResponseDTO<>(Constants.CREATED_CODE, Constants.CREATED, dtoResponse);
	}

	@Override
	public ServiceResponseDTO<Void> deleteProduct(String id) {

		ProductEntity entity = productRepository.getProductById(id);

		if (null == entity) {
			throw new ProductDoesNotExistException(Constants.PRODUCT_NOT_EXIST, Constants.BAD_REQUEST_CODE);
		}
		productRepository.deleteProduct(entity);

		return new ServiceResponseDTO<>(Constants.NO_CONTENT_CODE, Constants.NO_CONTENT, null);
	}

	protected ProductDTO commonCreateOrUpdateOperation(ProductDTO product) {

		ProductEntity entityToCreate = modelMapper.map(product, ProductEntity.class);

		ProductEntity createdEntity = productRepository.createOrUpdateProduct(entityToCreate);

		return modelMapper.map(createdEntity, ProductDTO.class);

	}

}
