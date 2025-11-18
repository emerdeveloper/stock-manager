package com.emegonza.stock.manager.service;

import com.emegonza.stock.manager.model.ProductDto;
import com.emegonza.stock.manager.model.ProductEntity;

public class Mapper {

    public static ProductDto productEntityToDto(ProductEntity entity) {
        return new ProductDto(entity.getId(),
                entity.getName(),
                entity.getEnteredByUser(),
                entity.getEnteredDate(),
                entity.getBuyingPrice(),
                entity.getSellingPrice(),
                entity.getLastModifiedDate(),
                entity.getLastModifiedByUser(),
                entity.getStatus());
    }

    public static ProductEntity productDtoToEntity(ProductDto product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.id());
        entity.setName(product.name());
        entity.setBuyingPrice(product.buyingPrince());
        entity.setEnteredByUser(product.enteredByUser());
        entity.setSellingPrice(product.sellingPrice());
        entity.setLastModifiedByUser(product.lastModifiedByUser());
        if (product.lastModifiedDate() != null) {
            entity.setLastModifiedDate(product.lastModifiedDate());
        }
        if (product.status() != null) {
            entity.setStatus(product.status());
        }
        return entity;
    }
}
