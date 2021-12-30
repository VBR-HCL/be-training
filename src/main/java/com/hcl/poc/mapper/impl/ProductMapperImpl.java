package com.hcl.poc.mapper.impl;

import com.hcl.poc.api.model.ProductCreate;
import com.hcl.poc.api.model.ProductResponse;
import com.hcl.poc.entity.Group;
import com.hcl.poc.entity.Product;
import com.hcl.poc.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProductMapperImpl implements ProductMapper {

    private final EntityManager entityManager;

    public ProductMapperImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        }

        final ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());

        return productResponse;
    }

    @Override
    public Product toProduct(ProductCreate productCreate) {
        if (productCreate == null) {
            return null;
        }

        final Product product = new Product();
        product.setName(productCreate.getName());
        product.setGroup(entityManager.getReference(Group.class, productCreate.getGroupId()));

        return product;
    }
}
