package com.AbdulKhaliq.EcommerceApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews_table")
@Getter
@Setter
public class Reviews
{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long reviewId;
     private  String review;
     private LocalDateTime reviewDate;
    @JoinColumn(name = "user_id")
    @ManyToOne
     private User user;
    @JoinColumn(name = "product_id")
    @ManyToOne
     private Product product;

}
