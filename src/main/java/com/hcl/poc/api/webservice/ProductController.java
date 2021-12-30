package com.hcl.poc.api.webservice;

import com.hcl.poc.api.model.ProductResponse;
import com.hcl.poc.api.model.ProductSearchRequest;
import com.hcl.poc.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hcl.poc.config.PocConfigProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<Page<ProductResponse>> find(ProductSearchRequest productSearchRequest, Pageable pageable) {
        return ResponseEntity.ok(productService.find(productSearchRequest, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}
