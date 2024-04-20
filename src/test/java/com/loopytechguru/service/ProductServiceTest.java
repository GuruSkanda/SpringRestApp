package com.loopytechguru.service;

import com.loopytechguru.entity.Product;
import com.loopytechguru.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    AutoCloseable autoCloseable;
    Product product;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository); // Inject the mock
        product = new Product(1, "book",6,100);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSaveProduct() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        System.out.println("productID"+productRepository.save(product).getId());
        assertThat(productService.saveProduct(product).getId()).isEqualTo(product.getId());
    }

    @Test
    void saveProducts() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.saveAll(new ArrayList<Product>(Collections.singleton(product)))).thenReturn(
                new ArrayList<Product>(Collections.singleton(product))
        );
        assertThat(productService.saveProducts(
                new ArrayList<Product>(Collections.singleton(product))).get(0).getName())
                .isEqualTo(product.getName());
    }

    @Test
    void testGetProducts() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findAll()).thenReturn(
                new ArrayList<Product>(Collections.singleton(product))
        );
        assertThat(productService.getProducts().get(0).getName()).isEqualTo(product.getName());
    }

    @Test
    void getProductById() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findById(product.getId()))
                .thenReturn(Optional.ofNullable(product));
        assertThat(productService.getProductById(product.getId())).isEqualTo(product);
    }

    @Test
    void getProductByName() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findByName(product.getName()))
                        .thenReturn(product);
        assertThat(productService.getProductByName(product.getName()).getName()).isEqualTo(product.getName());
    }

    @Test
    void testDeleteProduct() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doAnswer(Answers.CALLS_REAL_METHODS).when(
                productRepository).deleteById(Mockito.any());
        assertThat(productService.deleteProduct(product.getId())).isEqualTo("Product removed");
    }

    @Test
    void testUpdateProduct() {
        Mockito.mock(Product.class);
        Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);
        assertThat(productService.updateProduct(product).getId()).isEqualTo(product.getId());
    }
}