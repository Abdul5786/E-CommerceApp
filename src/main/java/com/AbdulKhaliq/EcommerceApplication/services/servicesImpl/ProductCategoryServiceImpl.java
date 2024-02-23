package com.AbdulKhaliq.EcommerceApplication.services.servicesImpl;

import com.AbdulKhaliq.EcommerceApplication.entities.ProductCategories;
import com.AbdulKhaliq.EcommerceApplication.exception.ResourceNotFoundException;
import com.AbdulKhaliq.EcommerceApplication.exception.ResponseMessageException;
import com.AbdulKhaliq.EcommerceApplication.payloads.ApiResponse;
import com.AbdulKhaliq.EcommerceApplication.payloads.ProductCategoryDto;
import com.AbdulKhaliq.EcommerceApplication.repositories.ProductCategoryRepo;
import com.AbdulKhaliq.EcommerceApplication.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl  implements ProductCategoryService
{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategoryDto addProductCategory(ProductCategoryDto productCategoryDto)
    {

        ProductCategories productCategory = this.modelMapper.map(productCategoryDto, ProductCategories.class);
        ProductCategories savedProductCategories = productCategoryRepo.findByProductCategory(productCategory.getProductCategory());
        if(savedProductCategories!=null)
        {
            throw  new ResponseMessageException("category name :" +productCategory.getProductCategory()+"already exist");
        }
                productCategoryRepo.save(productCategory);
                return this.modelMapper.map(productCategory,ProductCategoryDto.class);
    }

    @Override
    public List<ProductCategoryDto> getAllCategories(Integer pageNumber,Integer pageSize,String sortBy)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<ProductCategories> allPages = productCategoryRepo.findAll(pageRequest);
        List<ProductCategories> productCategoriesList = allPages.getContent();
        List<ProductCategoryDto> productCategoryDtoList = productCategoriesList.stream().map(e -> this.modelMapper.map(e, ProductCategoryDto.class)).collect(Collectors.toList());
        return productCategoryDtoList;
     }

    @Override
    public ProductCategoryDto updateProductCategoryByName(ProductCategoryDto productCategoryDto)
    {
        ProductCategories productCategory = productCategoryRepo.findByProductCategory(productCategoryDto.getProductCategory());
         if(productCategory==null)
         {
                  throw new ResourceNotFoundException("ProductCategory Not Found With ","productCategoryName "+productCategoryDto.getProductCategory(), productCategoryDto.getProductCatId());
         }

                productCategory.setDescription(productCategoryDto.getDescription());
                productCategory.setProductCategory(productCategoryDto.getProductCategory());
                ProductCategories updatedProductCategories = productCategoryRepo.save(productCategory);
                return this.modelMapper.map(updatedProductCategories,ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto getProductCategoryById(Long productCatId)
    {
        ProductCategories productCategories = productCategoryRepo.findById(productCatId).orElseThrow(() -> new ResourceNotFoundException("product not found with :", "productCatId", +productCatId));
          return this.modelMapper.map(productCategories,ProductCategoryDto.class);
    }

    @Override
    public String deleteProductCategory(Long productCatId)
    {
        ProductCategories productCategories = productCategoryRepo.findById(productCatId).orElseThrow(() -> new ResourceNotFoundException("produc", "productcatId", +productCatId));
        productCategoryRepo.delete(productCategories);
        return "product name "+productCategories.getProductCategory()+" deleted successfully";
    }
}
