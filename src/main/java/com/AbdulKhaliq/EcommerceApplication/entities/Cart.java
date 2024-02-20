package com.AbdulKhaliq.EcommerceApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_table")
@Getter
@Setter
public class Cart
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private  User user;

  @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  List<CartItem> cartItemList= new ArrayList<>();

  private Long totalPrice;

}
