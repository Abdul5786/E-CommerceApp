package com.AbdulKhaliq.EcommerceApplication.entities;

import com.AbdulKhaliq.EcommerceApplication.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_table")
@Getter
@Setter
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime orderDate;
    @OneToOne
    private Address ShippingAddress;
    private OrderStatus orderStatus;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    private Double totalAmount;

}
