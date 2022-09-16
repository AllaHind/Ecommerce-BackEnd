package com.alla.ecommerce.dao;

import com.alla.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByProductCode(String code);
    Product findByProductName(String name);

}
