package com.emegonza.stock.manager.service;

import com.emegonza.stock.manager.model.ProductDto;
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
}
