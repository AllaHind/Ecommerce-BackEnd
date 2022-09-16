package com.alla.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private  String productName;
    private String productDescription;
    private Double actualPrice;
    private Double discountedPrice;
    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)

    @JoinTable(name = "product_images",
    joinColumns = {
            @JoinColumn(name = "PRODUCT_ID")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "IMAGE_ID")
            }
    )
    private Set<ImageModel> productImages;

}
