package com.app.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;
    private String imageUrl;
    private Boolean isPublic;
    private Integer quantity;
    private Integer rating;
}
