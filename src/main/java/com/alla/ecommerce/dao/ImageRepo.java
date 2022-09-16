package com.alla.ecommerce.dao;

import com.alla.ecommerce.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepo extends JpaRepository<ImageModel,Long> {

}
