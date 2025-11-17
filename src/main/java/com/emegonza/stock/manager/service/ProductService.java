package com.emegonza.stock.manager.service;

import com.emegonza.stock.manager.model.ProductDto;
import com.emegonza.stock.manager.model.ProductEntity;
import com.emegonza.stock.manager.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class ProductService {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    public Optional<ProductDto> getProduct(int id) {
        return repository.findById(id)
                .map(Mapper::productEntityToDto);
    }

    public Optional<ProductDto> saveProduct(ProductDto product) {
        return Optional.ofNullable(product.id())
                .filter(id -> repository.findById(id).isEmpty())
                .map(id -> {
                    ProductEntity entity = repository.save(Mapper.productDtoToEntity(product));
                    return Mapper.productEntityToDto(entity);
                })
                .or(() -> {
                    ProductEntity entity = repository.save(Mapper.productDtoToEntity(product));
                    return Optional.of(Mapper.productEntityToDto(entity));
                });
    }
}
