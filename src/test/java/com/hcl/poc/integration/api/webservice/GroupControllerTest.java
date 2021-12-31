package com.hcl.poc.integration.api.webservice;

import static com.hcl.poc.config.PocConfigProperties.BASE_URL;
import static com.hcl.poc.integration.api.Utils.DOMAIN;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GroupControllerTest {

  private static final String RESOURCE = "/groups";

  @Test
  public void testGetGroups() {
    Response response = RestAssured.get(DOMAIN + BASE_URL + RESOURCE);
    response.then().body("content.id", hasItems(1, 2, 3));
    response.then().body("content.name", hasItems("Mortgage", "Payments", "Savings"));
    response.then().body("last", equalTo(true));
    response.then().body("first", equalTo(true));
    response.then().body("size", equalTo(20));
    response.then().body("totalElements", equalTo(3));

  }

  @Test
  public void testGetGroupsById() {
    Response response = RestAssured.get(DOMAIN + BASE_URL + RESOURCE + "/1");
    response.then().body("id", equalTo(1));
    response.then().body("name", equalTo("Mortgage"));
  }
}
