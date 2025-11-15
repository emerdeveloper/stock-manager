package com.emegonza.stock.manager.service;

import com.emegonza.stock.manager.model.ProductDto;
import com.emegonza.stock.manager.model.ProductEntity;

public class Mapper {

    public static ProductDto productEntityToDto(ProductEntity entity) {
        return new ProductDto(entity.getName(),
                entity.getEnteredByUser(),
                entity.getEnteredDate().toString(),
                entity.getBuyingPrice(),
                entity.getSellingPrice(),
                entity.getLastModifiedDate().toString(),
                entity.getLastModifiedByUser(),
                entity.getStatus());
    }
}
