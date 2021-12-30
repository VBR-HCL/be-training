package com.hcl.poc.mapper;


import com.hcl.poc.api.model.ProductCreate;
import com.hcl.poc.api.model.ProductResponse;
import com.hcl.poc.entity.Product;

public interface ProductMapper {

    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductCreate productCreate);
}
