package com.enoca.challenge.atakanaksoy;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.entities.concretes.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class TestDataCreator {
    private final String BASE_URL = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void createCustomer() throws Exception {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setFirstName("Atakan");
        createCustomerRequest.setLastName("Aksoy");
        createCustomerRequest.setEmail("atkaksoy501@hotmail.com");
        createCustomerRequest.setPhoneNumber("05301234567");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String customerJson = objectMapper.writeValueAsString(createCustomerRequest);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/customers/add")
                .contentType("application/json")
                .content(customerJson));

        result.andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void createProduct() throws Exception{
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Product 1");
        createProductRequest.setPrice(99.9);
        createProductRequest.setStock(100);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String productJson = objectMapper.writeValueAsString(createProductRequest);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/products/add")
                .contentType("application/json")
                .content(productJson));

        result.andExpect(status().isCreated());
    }

}
