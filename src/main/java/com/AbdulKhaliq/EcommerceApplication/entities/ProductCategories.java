package com.AbdulKhaliq.EcommerceApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product-categories")
@Getter
@Setter
public class ProductCategories
{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCatId;
    private String productCategory;
    private String description;
    @OneToMany(mappedBy = "productCategories" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> product;

    @Override
    public String toString() {
        return "ProductCategories{" +
                "productCatId=" + productCatId +
                ", productCategory='" + productCategory + '\'' +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }
}










