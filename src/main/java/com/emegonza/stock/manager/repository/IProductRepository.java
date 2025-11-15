package com.emegonza.stock.manager.repository;

import com.emegonza.stock.manager.enums.ProductStatus;
import com.emegonza.stock.manager.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByStatusAndEnteredByUser(ProductStatus status, String enteredByUser);
}
