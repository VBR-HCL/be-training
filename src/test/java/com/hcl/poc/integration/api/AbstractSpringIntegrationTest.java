package com.hcl.poc.integration.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AbstractSpringIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    private void resetState() {
        cleanAllDatabases();
    }

    private void cleanAllDatabases() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "poc_product", "poc_group");
    }

    protected <T> T mapToObject(MvcResult mvcResult, Class<T> valueType) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), valueType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not parse response to " + valueType.getName());
        }
    }
}
