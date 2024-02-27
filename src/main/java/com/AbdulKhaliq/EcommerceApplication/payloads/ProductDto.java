package com.AbdulKhaliq.EcommerceApplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto
{
     private Long productId;
     private String title;
     private int price;
     private  int discountedPrice;
     private int  quantities;
     private  String  brand ;
     private ProductCategoryDto productCategories;

     @Override
     public String toString() {
          return "ProductDto{" +
                  "productId=" + productId +
                  ", title='" + title + '\'' +
                  ", price=" + price +
                  ", discountedPrice=" + discountedPrice +
                  ", quantities=" + quantities +
                  ", brand='" + brand + '\'' +
                  ", productCategories=" + productCategories +
                  '}';
     }
}
