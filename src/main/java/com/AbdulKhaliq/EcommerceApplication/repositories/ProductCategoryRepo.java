package com.AbdulKhaliq.EcommerceApplication.repositories;

import com.AbdulKhaliq.EcommerceApplication.entities.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepo  extends JpaRepository<ProductCategories,Long>
{

    ProductCategories findByProductCategory(String productCategory);
}
