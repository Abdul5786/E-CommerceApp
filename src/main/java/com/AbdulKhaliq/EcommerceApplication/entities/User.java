package com.AbdulKhaliq.EcommerceApplication.entities;

import com.AbdulKhaliq.EcommerceApplication.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class User  implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long userId;
    @Column(name = "first_Name")
   private String firstName;
    @Column(name = "last_Name")
   private String lastName;
    @Column(name = "password")
   private String password;
    @Column(name = "email")
   private String email;
    @Column(name = "mobile_No")
   private String mobileNo;
    @Column(name = "user_role")
   private Roles roles;
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   private List<Address> address= new ArrayList<>();
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  Cart cart;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orderList= new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getUsername()
    {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // in future i will add these functionalities.
//   @OneToMany(mappedBy = "")
//   private List<PaymentInformation> paymentInformation= new ArrayList<>();
//   @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//   private List<Ratings> ratings = new ArrayList<>();
//   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//   private List<Reviews> reviews= new ArrayList<>();
//----------------------------i will do it in future--------------------------------------------------------------------
 }









