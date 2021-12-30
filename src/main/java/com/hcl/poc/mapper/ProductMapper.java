package com.hcl.poc.mapper;


import com.hcl.poc.api.model.product.ProductCreate;
import com.hcl.poc.api.model.product.ProductResponse;
import com.hcl.poc.entity.Product;

public interface ProductMapper {

    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductCreate productCreate);
}
