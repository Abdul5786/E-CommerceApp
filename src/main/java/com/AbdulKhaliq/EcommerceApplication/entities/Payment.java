package com.AbdulKhaliq.EcommerceApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Order order;
    private String paymentMethod;
}
