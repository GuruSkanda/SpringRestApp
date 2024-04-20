package com.loopytechguru.repository;

import com.loopytechguru.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@DataJdbcTest
public class ProductRepositoryTest {
    /**
     * 1. GIVEN
     * 2. WHEN
     * 3. THEN
     */
    @Autowired
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1, "book",6,100);
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        product = null;
        productRepository.deleteAll();
    }

    // Test case for success scenario.
    @Test
    void testFindByName(){
        Product productResponse = productRepository.findByName("book");
        System.out.println("product response"+productResponse);
        System.out.println("product response id"+productResponse.getId());
        assertThat(productResponse.getId()).isEqualTo(product.getId());
    }

    // Test case for failure scenario.
    @Test
    void testNotFindByName(){
        Product productResponse = productRepository.findByName("bag");
        System.out.println("product response"+productResponse);
        assertThat(productResponse).isEqualTo(null);
    }
}
