package com.emegonza.stock.manager.model;

import com.emegonza.stock.manager.enums.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String enteredByUser;
    @CreationTimestamp
    private Timestamp enteredDate;
    private double buyingPrice;
    private double sellingPrice;
    private Date lastModifiedDate = new Date();
    private String lastModifiedByUser;
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.AVAILABLE;
}
