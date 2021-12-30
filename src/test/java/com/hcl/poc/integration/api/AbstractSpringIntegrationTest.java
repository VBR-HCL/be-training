package com.hcl.poc.integration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
    private ObjectMapper objectMapper;

    protected <T> T mapToObject(MvcResult mvcResult, Class<T> valueType) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), valueType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not parse response to " + valueType.getName());
        }
    }
}
