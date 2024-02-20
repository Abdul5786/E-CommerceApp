package com.AbdulKhaliq.EcommerceApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product_table")
@Getter
@Setter
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String title;
    private int price;
    private int discountedPrice;
    private int  quantities;
    private int  brand ;
    @JoinColumn(name = "product_categoriesId")
    @ManyToOne
    private ProductCategories productCategories;

    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Ratings> ratings= new ArrayList<>();
    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Reviews> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartItem> cartItemList= new ArrayList<>();


}
