package com.AbdulKhaliq.EcommerceApplication.controllers;

import com.AbdulKhaliq.EcommerceApplication.config.AppConstants;
import com.AbdulKhaliq.EcommerceApplication.payloads.ApiResponse;
import com.AbdulKhaliq.EcommerceApplication.payloads.ProductCategoryDto;
import com.AbdulKhaliq.EcommerceApplication.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productCategory")
public class ProductCategoryControllers
{

    @Autowired
    private ProductCategoryService productCategoryService;

    public ResponseEntity<ProductCategoryDto> addProductCategory(@RequestBody ProductCategoryDto productCategoryDto)
    {
        ProductCategoryDto saved = productCategoryService.addProductCategory(productCategoryDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    public ResponseEntity<List<ProductCategoryDto>>  getAllProductCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                             @RequestParam(name ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Integer pageSize,
                                                                             @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_PRODUCT_CATEGORY_BY,required = false) String sortOrder)
    {
        List<ProductCategoryDto> allCategories = productCategoryService.getAllCategories(pageNumber, pageSize, sortOrder);
        return new ResponseEntity<>(allCategories,HttpStatus.FOUND);
    }

    public ResponseEntity<ProductCategoryDto>   updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto)
    {
        ProductCategoryDto updatedProductCategory = productCategoryService.updateProductCategoryByName(productCategoryDto);
        return new ResponseEntity<>(updatedProductCategory,HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> deleteProductCategoryById(@PathVariable Long productCatId)
    {
        String s = productCategoryService.deleteProductCategory(productCatId);
        ApiResponse apiResponse = new ApiResponse(s, true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }



}
