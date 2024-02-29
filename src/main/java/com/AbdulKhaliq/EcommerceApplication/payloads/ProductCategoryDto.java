package com.AbdulKhaliq.EcommerceApplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto
{
    private Long productCatId;
    private String productCategory;
    private String description;

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "productCatId=" + productCatId +
                ", productCategory='" + productCategory + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
