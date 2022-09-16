package com.alla.ecommerce.service;


import com.alla.ecommerce.dao.ProductRepository;
import com.alla.ecommerce.entity.ImageModel;
import com.alla.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product getProduct(String code) {
        return productRepository.findByProductCode(code);
    }

    @Override
    public ResponseEntity<String> saveProduct(Product product) {
        if (getProduct(product.getProductCode()) != null) {
            return new ResponseEntity<String>("Code already exists!", HttpStatus.BAD_REQUEST);
        } else if (product.getActualPrice() <= product.getDiscountedPrice()) {

            return new ResponseEntity<String>("Actual price should be greater than Discounted Price!", HttpStatus.BAD_REQUEST);

        } else productRepository.save(product);
        return new ResponseEntity<String>("Product has been saved successfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateProduct(Product product, Long id) {
        product.setId(id);
         if (product.getActualPrice() <= product.getDiscountedPrice()) {
            return new ResponseEntity<String>("Actual price should be greater than Discounted Price!", HttpStatus.BAD_REQUEST);

        } else productRepository.save(product);
        return new ResponseEntity<String>("Product details has been updated successfully!", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteProduct(Long id) {


        productRepository.deleteById(id);

        return new ResponseEntity<String>("Product has been deleted successfully!", HttpStatus.OK);
    }

    @Override
    public List<Product> listProducts() {

        return productRepository.findAll();
    }


}
