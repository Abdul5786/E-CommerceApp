package com.AbdulKhaliq.EcommerceApplication.services.servicesImpl;

import com.AbdulKhaliq.EcommerceApplication.entities.Product;
import com.AbdulKhaliq.EcommerceApplication.entities.ProductCategories;
import com.AbdulKhaliq.EcommerceApplication.exception.ResourceNotFoundException;
import com.AbdulKhaliq.EcommerceApplication.exception.ResponseMessageException;
import com.AbdulKhaliq.EcommerceApplication.payloads.ApiResponse;
import com.AbdulKhaliq.EcommerceApplication.payloads.ProductDto;
import com.AbdulKhaliq.EcommerceApplication.repositories.ProductCategoryRepo;
import com.AbdulKhaliq.EcommerceApplication.repositories.ProductRepo;
import com.AbdulKhaliq.EcommerceApplication.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto AddProduct(ProductDto productDto,Long productCatId)
    {
        ProductCategories productCategories = productCategoryRepo.findById(productCatId).orElseThrow(() -> new ResourceNotFoundException("ProductCategory not found :", "productCatId", +productCatId));
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setProductCategories(productCategories);
        productRepo.save(product);
        return this.modelMapper.map(product,ProductDto.class);
    }
    @Override
    public List<ProductDto> getAllProducts(Integer pageNumber,Integer pageSize,String sortBY)
    {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBY).ascending());
        Page<Product> allProduct = productRepo.findAll(pageRequest);
        List<Product> allProductList = allProduct.getContent();
        List<ProductDto> productDtoList = allProductList.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
//        for (ProductDto p : productDtoList
//        ){
//            System.out.println(p.getProductCategories());
//
//    }
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long catId, Integer pageSize, String sortBy, String sortingOrder)
    {

        return  null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId)
    {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with", "productId", productId));
                         product.setPrice(productDto.getPrice());
                         product.setBrand(product.getBrand());
                         product.setQuantities(productDto.getQuantities());
                         product.setDiscountedPrice(productDto.getDiscountedPrice());
                         productRepo.save(product);
                         return this.modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> searchProductByKeyword(String keyword,Integer pageNumber,Integer pageSize,String sortBY)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBY).ascending());
        Page<Product> pageProduct = productRepo.findByTitle(keyword, pageRequest);
        List<Product> searchProductList = pageProduct.getContent();
        if(searchProductList.size()==0)
        {
            throw new ResponseMessageException("product not found"+keyword);
        }

        List<ProductDto> productDtoList = searchProductList.stream().map(p -> this.modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
          return productDtoList;
    }

    @Override
    public String deleteProduct(Long productId)
    {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with", "productId", productId));
                 productRepo.delete(product);
                 return "product deleted successfully";
    }
}
