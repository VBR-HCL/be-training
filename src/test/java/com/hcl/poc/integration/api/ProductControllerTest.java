package com.hcl.poc.integration.api;

import com.hcl.poc.api.model.exception.ExceptionResponse;
import com.hcl.poc.api.model.product.ProductResponse;
import com.hcl.poc.config.PocConfigProperties;
import com.hcl.poc.entity.Product;
import com.hcl.poc.integration.api.model.ProductPageResponse;
import com.hcl.poc.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MvcResult;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractSpringIntegrationTest {

    private static final String BASE_URL = PocConfigProperties.BASE_URL + "/products";

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test_getProductsWithDefaultPageable() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();
        final ProductPageResponse productPageResponse = mapToObject(mvcResult, ProductPageResponse.class);

        long productCount = productRepository.count();

        assertThat(productPageResponse.getTotalElements()).isEqualTo(productCount);
        assertThat(productPageResponse.getNumberOfElements()).isEqualTo(productCount);
        assertThat(productPageResponse.getSize()).isEqualTo(20);
        assertThat(productPageResponse.getNumber()).isEqualTo(0);
        assertThat(productPageResponse.isFirst()).isTrue();
        assertThat(productPageResponse.isLast()).isTrue();
        assertThat(productPageResponse.isEmpty()).isFalse();
    }

    @Test
    public void test_getProductsFromOneCategory() throws Exception {
        final Long groupId = 1L;
        final MvcResult mvcResult = mockMvc.perform(get(BASE_URL)
                .param("groupId", groupId.toString()))
                .andExpect(status().isOk())
                .andReturn();

        final Page<Product> productsInGroup = productRepository.findAllByGroup_Id(groupId, PageRequest.of(0, 20));

        final ProductPageResponse productPageResponse = mapToObject(mvcResult, ProductPageResponse.class);
        assertThat(productPageResponse.getTotalElements()).isEqualTo(productsInGroup.getTotalElements());
        assertThat(productPageResponse.getNumberOfElements()).isEqualTo(productsInGroup.getNumberOfElements());
        assertThat(productPageResponse.getSize()).isEqualTo(20);
        assertThat(productPageResponse.getNumber()).isEqualTo(0);
        assertThat(productPageResponse.getContent().stream()
                .map(ProductResponse::getId)
                .collect(Collectors.toSet()))
                .containsExactlyElementsOf(productsInGroup.stream()
                        .map(Product::getId)
                        .collect(Collectors.toSet()));
    }

    @Test
    public void test_getProductById() throws Exception {
        final Long productId = productRepository.findAll().get(0).getId();
        final MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/" + productId))
                .andExpect(status().isOk())
                .andReturn();
        final ProductResponse productResponse = mapToObject(mvcResult, ProductResponse.class);

        final Product product = productRepository.findById(productId).get();

        assertThat(productResponse.getId()).isEqualTo(product.getId()).isEqualTo(productId);
        assertThat(productResponse.getName()).isEqualTo(product.getName());
    }

    @Test
    public void test_getProductByInvalidIdThrowsNotFoundException() throws Exception {
        final Long productId = 999999L;
        final MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/" + productId))
                .andExpect(status().isNotFound())
                .andReturn();

        final ExceptionResponse exceptionResponse = mapToObject(mvcResult, ExceptionResponse.class);

        assertThat(exceptionResponse.getMessage()).isEqualTo("Could not find Product with id: " + productId);
    }
}
