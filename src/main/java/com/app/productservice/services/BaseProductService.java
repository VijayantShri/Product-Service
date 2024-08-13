package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseProductService {

    Product getProductById(Long id)  throws ProductNotFoundException;

    List<Product> getAllProducts();

    Page<Product> getAllProducts(int page, int size);

    Product deleteProductById(Long id) throws ProductNotFoundException;

    Product addProduct(Product product);

    Product updateProduct(Long id, Product updatedProduct) throws ProductNotFoundException;
}
