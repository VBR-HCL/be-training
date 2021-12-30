package com.hcl.poc.initializer;

import com.hcl.poc.api.model.GroupCreate;
import com.hcl.poc.api.model.ProductCreate;
import com.hcl.poc.service.GroupService;
import com.hcl.poc.service.ProductService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final GroupService groupService;
    private final ProductService productService;

    public DataInitializer(GroupService groupService,
                           ProductService productService) {
        this.groupService = groupService;
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        initializeGroups();
        initializeProducts();
    }

    private void initializeGroups() {
        groupService.save(new GroupCreate(1L, "Mortgage"));
        groupService.save(new GroupCreate(2L, "Payments"));
        groupService.save(new GroupCreate(3L, "Savings"));
    }

    private void initializeProducts() {
        productService.save(new ProductCreate("Bank saving mortgage", 1L));
        productService.save(new ProductCreate("Interest-only", 1L));
        productService.save(new ProductCreate("Annuity", 1L));
        productService.save(new ProductCreate("Linear", 1L));
        productService.save(new ProductCreate("Payment account", 2L));
        productService.save(new ProductCreate("Debit card", 2L));
        productService.save(new ProductCreate("Quarterly limit", 2L));
        productService.save(new ProductCreate("Continuously limit", 2L));
        productService.save(new ProductCreate("Orange saving account", 3L));
        productService.save(new ProductCreate("Child saving account", 3L));
        productService.save(new ProductCreate("Saving account for Unicef", 3L));
        productService.save(new ProductCreate("Bonus interest account", 3L));
        productService.save(new ProductCreate("Savings deposit", 3L));
        productService.save(new ProductCreate("Green savings deposit", 3L));
    }
}
