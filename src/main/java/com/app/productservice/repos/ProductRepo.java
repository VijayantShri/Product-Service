package com.app.productservice.repos;

import com.app.productservice.models.Product;
import com.app.productservice.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    List<Product> findAll();

    @Query("select p from Product p where p.title = :t")
    List<Product> findByTitle(@Param("t") String title);

    @Query("SELECT p.id as Id, p.title as title FROM Product p WHERE p.title = :title")
    List<ProductWithIdAndTitle> findProductByTitle(String title);
}
