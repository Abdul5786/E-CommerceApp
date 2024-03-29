package com.AbdulKhaliq.EcommerceApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String title;
    private int price;
    private String image;
    private int discountedPrice;
    private int  quantities;
    private String  brand ;
    @JoinColumn(name = "product_categoriesId")
    @ManyToOne
    private ProductCategories productCategories;
    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartItem> cartItemList= new ArrayList<>();


//    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Ratings> ratings= new ArrayList<>();
//    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Reviews> reviews = new ArrayList<>();


}
