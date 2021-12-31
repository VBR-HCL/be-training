package com.hcl.poc.integration.api.webservice;

import static com.hcl.poc.config.PocConfigProperties.BASE_URL;
import static com.hcl.poc.integration.api.Utils.DOMAIN;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ProductControllerTest {

  private static final String RESOURCE = "/products";

  @Test
  public void testGetProducts() {
    Response response = RestAssured.get(DOMAIN + BASE_URL + RESOURCE);
    System.out.println(response.getBody().prettyPrint());
    response.then().body("content.id", hasItems(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    response.then().body("content.name", hasItems("Bank saving mortgage", "Interest-only", "Annuity", "Linear",
        "Payment account", "Debit card", "Quarterly limit", "Continuously limit", "Orange saving account", "Child saving account",
        "Saving account for Unicef", "Bonus interest account", "Savings deposit", "Green savings deposit"));
    response.then().body("last", equalTo(true));
    response.then().body("first", equalTo(true));
    response.then().body("size", equalTo(20));
    response.then().body("totalElements", equalTo(14));

  }

  @Test
  public void testGetProductsById() {
    Response response = RestAssured.get(DOMAIN + BASE_URL + RESOURCE + "/1");
    System.out.println(response.getBody().prettyPrint());
    response.then().body("id", equalTo(1));
    response.then().body("name", equalTo("Interest-only"));
  }
}
