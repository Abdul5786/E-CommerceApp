package com.AbdulKhaliq.EcommerceApplication.repositories;

import com.AbdulKhaliq.EcommerceApplication.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>
{
     Page<Product> findByTitle(String Title, Pageable pageable);
}
