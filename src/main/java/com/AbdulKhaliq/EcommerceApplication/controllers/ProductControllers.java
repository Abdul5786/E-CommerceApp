package com.AbdulKhaliq.EcommerceApplication.controllers;

import com.AbdulKhaliq.EcommerceApplication.config.AppConstants;
import com.AbdulKhaliq.EcommerceApplication.payloads.ProductDto;
import com.AbdulKhaliq.EcommerceApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductControllers
{

    @Autowired
    private ProductService productService;


   @PostMapping(value = "/addProduct/{productCatId}")
   public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto,@PathVariable  Long productCatId)
   {
       ProductDto addedProduct = productService.AddProduct(productDto, productCatId);
       return new ResponseEntity<>(addedProduct, HttpStatus.FOUND);
   }


   @GetMapping(value = "/getAllProduct")
   public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                          @RequestParam(name ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Integer pageSize,
                                                          @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required = false) String sortOrder
                                                         )
   {
       List<ProductDto> allProducts = productService.getAllProducts(pageNumber, pageSize, sortOrder);
       return new ResponseEntity<>(allProducts,HttpStatus.FOUND);
   }
    @GetMapping(value = "/searchProduct")
   public ResponseEntity<List<ProductDto>> getProductBySearchingProductName(@RequestParam(name = "productName") String productName,
                                                                            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                            @RequestParam(name ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Integer pageSize,
                                                                            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required = false) String sortOrder)
   {
       List<ProductDto> dtoList = productService.searchProductByKeyword(productName, pageNumber, pageSize, sortOrder);
       return new ResponseEntity<>(dtoList,HttpStatus.FOUND);
   }
}
