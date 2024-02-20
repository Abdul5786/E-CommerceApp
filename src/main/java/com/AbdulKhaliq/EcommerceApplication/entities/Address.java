package com.AbdulKhaliq.EcommerceApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address
{
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private  Long addressId;
     @Column(name = "street_address")
     private String streetAddress;
     @Column(name = "city")
     private String city;
     @Column(name = "state")
     private String state;
     @Column(name = "zip_Code")
     private String zipCode;
     @Column(name = "mobile_no")
     private String mobileNo;
     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;

}
