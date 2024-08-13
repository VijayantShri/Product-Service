package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.models.Product;
import com.app.productservice.models.ProductCategory;
import com.app.productservice.repos.CategoryRepo;
import com.app.productservice.repos.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class ProductService implements BaseProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);

//        if (product.isPresent()) {
//            ProductCategory productCategory = product.get().getCategory();
//        }

        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepo.findAll(pageable);
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepo.deleteById(id);
        return product.get();
    }

    @Override
    public Product addProduct(Product product) {
        Optional<ProductCategory> productCategory = this.categoryRepo.findByName(product.getCategory().getName());
        if (productCategory != null && productCategory.isPresent()) {
            product.setCategory(productCategory.get());
        } else {
            ProductCategory category = this.categoryRepo.save(product.getCategory());
            product.setCategory(category);
        }
        return this.productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) throws ProductNotFoundException {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        updatedProduct.setId(id);

        Optional<ProductCategory> productCategory = this.categoryRepo.findByName(updatedProduct.getCategory().getName());
        if (productCategory != null && productCategory.isPresent()) {
            updatedProduct.setCategory(productCategory.get());
        } else {
            ProductCategory category = this.categoryRepo.save(updatedProduct.getCategory());
            updatedProduct.setCategory(category);
        }

        return this.productRepo.save(updatedProduct);
    }
}
