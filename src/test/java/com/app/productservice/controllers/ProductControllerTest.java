package com.app.productservice.controllers;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.models.Product;
import com.app.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException, AccessDeniedException {
        // Arrange
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("dummy title");
        when(productService.getProductById(1l)).thenReturn(dummyProduct);

        // Act
        Product p = productController.getProductById(1L);

        // Assertion
        assertEquals(1L, p.getId());
    }

    @Test
    void getProductByIdNotFound() throws ProductNotFoundException {
        // Arrange
        when(productService.getProductById(1l)).thenThrow(new ProductNotFoundException("Product not found"));

        // Act
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1l));
    }


}