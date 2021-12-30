package com.hcl.poc.service.impl;

import com.hcl.poc.api.model.exception.NotFoundException;
import com.hcl.poc.api.model.product.ProductCreate;
import com.hcl.poc.api.model.product.ProductResponse;
import com.hcl.poc.api.model.product.ProductSearchRequest;
import com.hcl.poc.entity.Product;
import com.hcl.poc.mapper.ProductMapper;
import com.hcl.poc.repository.ProductRepository;
import com.hcl.poc.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> find(ProductSearchRequest productSearchRequest, Pageable pageable) {
        final Specification<Product> query = createQuery(productSearchRequest);

        return productRepository.findAll(query, pageable).map(productMapper::toProductResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return productMapper.toProductResponse(productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Could not find Product with id: " + id)));
    }

    @Override
    @Transactional
    public ProductResponse save(ProductCreate productCreate) {
        final Product savedProduct = productRepository.save(productMapper.toProduct(productCreate));
        return productMapper.toProductResponse(savedProduct);
    }


    private Specification<Product> createQuery(ProductSearchRequest productSearchRequest) {
        if (productSearchRequest == null || productSearchRequest.getGroupId() == null) {
            return unrestricted();
        }

        return groupId(productSearchRequest.getGroupId());
    }

    private Specification<Product> unrestricted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and();
    }

    private Specification<Product> groupId(Long groupId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("group").get("id"), groupId);
    }
}
