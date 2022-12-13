package com.bbk.api.ProductService.service;

import com.bbk.api.ProductService.model.ProductRequest;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	void reduceQuantity(long productId, long quantity);

}
