package com.AbdulKhaliq.EcommerceApplication.services;

import com.AbdulKhaliq.EcommerceApplication.payloads.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService
{
    ProductDto AddProduct(ProductDto  productDto,Long ProductCatId);
    List<ProductDto> getAllProducts(Integer pageNumber,Integer pageSize,String sortBY);
    List<ProductDto> getProductsByCategory(Long catId,Integer pageSize,String sortBy,String sortingOrder); // to search order by category

    ProductDto getProductById(Long productId);
    ProductDto updateProduct(ProductDto productDto,Long productId);

//   ProductDto updateProductImage()  i will do it later

    List<ProductDto> searchProductByKeyword(String keyword,Integer pageNumber,Integer pageSize,String sortBY);

    String deleteProduct(Long productId);

}
