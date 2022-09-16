package com.alla.ecommerce.service;

import com.alla.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    Product getProduct(String code);

    ResponseEntity<String> saveProduct(Product product);

    ResponseEntity<String> updateProduct(Product product, Long id);

    ResponseEntity<String> deleteProduct(Long id);

    List<Product> listProducts();


}
