package com.AbdulKhaliq.EcommerceApplication.controllers;

import com.AbdulKhaliq.EcommerceApplication.config.AppConstants;
import com.AbdulKhaliq.EcommerceApplication.payloads.ProductDto;
import com.AbdulKhaliq.EcommerceApplication.services.FileService;
import com.AbdulKhaliq.EcommerceApplication.services.ProductService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductControllers
{

    @Autowired
    private ProductService productService;

    @Autowired
   private FileService fileService;
   @Value("${project.image}")
   private String path;
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
    @PostMapping(value = "/image/upload/{productId}")
   public ResponseEntity<ProductDto> uploadProductImage(@RequestParam("image")MultipartFile image,@PathVariable Long productId) throws IOException
   {
       ProductDto productDto = productService.getProductById(productId);
       String fileName = fileService.uploadImage(path, image);
       productDto.setImage(fileName);
       ProductDto updateProductDto = productService.updateProduct(productDto,productId);
       return new ResponseEntity<>(updateProductDto,HttpStatus.OK);
   }

   // method to serve files
     @GetMapping(value = "/getImage/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
                       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
