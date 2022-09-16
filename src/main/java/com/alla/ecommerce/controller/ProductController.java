package com.alla.ecommerce.controller;

import com.alla.ecommerce.entity.ImageModel;
import com.alla.ecommerce.entity.Product;
import com.alla.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProduct/{code}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public Product getProduct(@PathVariable("code") String code) {
        return productService.getProduct(code);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> saveProduct(@RequestPart("product") Product product,
                                              @RequestPart("imageFile") MultipartFile[] file) {


        try {
            Set<ImageModel> imageModels = uploadImage(file);
            product.setProductImages(imageModels);
            return productService.saveProduct(product);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/listProducts")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {

        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(null,
                    file.getContentType(), file.getOriginalFilename()
                    , file.getBytes()
            );
            imageModels.add(imageModel);
        }


        return imageModels;
    }


}
