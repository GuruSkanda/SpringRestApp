package com.loopytechguru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.loopytechguru.entity.Product;
import com.loopytechguru.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    Product productOne;
    Product productTwo;
    List<Product> productList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        productOne = new Product(1, "book",6,100);
        productTwo = new Product(2, "pen",2,20);
        productList.add(productOne);
        productList.add(productTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddProduct() throws  Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(productOne);

        Mockito.when(productService.saveProduct(productOne))
                .thenReturn(productOne);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addProducts() {
    }

    @Test
    void findAllProducts()  throws Exception{
        Mockito.when(productService.getProducts())
                .thenReturn(productList);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void findProductById() throws Exception{
        Mockito.when(productService.getProductById(1))
                .thenReturn(productOne);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/productById/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void findProductByName() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void testDeleteProduct() throws Exception{
        Mockito.when(productService.deleteProduct(1))
                .thenReturn("Product removed");
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1"))
                .andDo(print()).andExpect(status().isOk());

    }
}