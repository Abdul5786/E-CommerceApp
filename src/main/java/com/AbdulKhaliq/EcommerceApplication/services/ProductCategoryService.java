package com.AbdulKhaliq.EcommerceApplication.services;

import com.AbdulKhaliq.EcommerceApplication.payloads.ProductCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService
{
    ProductCategoryDto addProductCategory(ProductCategoryDto productCategoryDto);
    List<ProductCategoryDto> getAllCategories(Integer pageNumber,Integer pageSize,String sortBy);
    ProductCategoryDto updateProductCategoryByName(ProductCategoryDto productCategoryDto);

    ProductCategoryDto  getProductCategoryById(Long productCatId);

    String deleteProductCategory(Long Category);



}
