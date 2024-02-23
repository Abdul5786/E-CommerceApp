package com.AbdulKhaliq.EcommerceApplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto
{
    private Long productId;
    private String title;
    private int price;
    private int discountedPrice;
    private int  quantities;
    private int  brand ;
    private ProductCategoryDto productCategoryDto;
}
