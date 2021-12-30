package com.hcl.poc.service;

import com.hcl.poc.api.model.product.ProductCreate;
import com.hcl.poc.api.model.product.ProductResponse;
import com.hcl.poc.api.model.product.ProductSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> find(ProductSearchRequest productSearchRequest, Pageable pageable);

    ProductResponse findById(Long id);

    ProductResponse save(ProductCreate productCreate);
}
