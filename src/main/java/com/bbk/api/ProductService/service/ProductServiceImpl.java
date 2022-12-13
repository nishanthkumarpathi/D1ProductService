package com.bbk.api.ProductService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbk.api.ProductService.entity.Product;
import com.bbk.api.ProductService.model.ProductRequest;
import com.bbk.api.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {

		Product product = Product.builder().productName(productRequest.getName()).quantity(productRequest.getQuantity())
				.price(productRequest.getPrice()).build();

		productRepository.save(product);

		return product.getProductId();
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {

		log.info("Reduce the Quantity: {} for Id: {}", quantity, productId);

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product with Given Id not Found"));
		
		if(product.getQuantity() < quantity) {
			throw new RuntimeException("Out of Stock, or You Order More then What we have");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		
		log.info("Product Quantity is reduced uccessfully");


	}

}
