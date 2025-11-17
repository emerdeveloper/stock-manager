package com.emegonza.stock.manager.service;

import com.emegonza.stock.manager.enums.ProductStatus;
import com.emegonza.stock.manager.model.ProductDto;
import com.emegonza.stock.manager.model.ProductEntity;
import com.emegonza.stock.manager.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<ProductDto> updateProduct(Integer id, ProductDto product) {
        return Optional.ofNullable(id)
                .filter(idFiltered -> repository.findById(idFiltered).isPresent())
                .map(idFiltered -> {
                    ProductEntity entity = Mapper.productDtoToEntity(product);
                    entity.setId(idFiltered);
                    ProductEntity entityUpdated = repository.save(entity);
                    return Mapper.productEntityToDto(entityUpdated);
                })
                .or(Optional::empty);
    }

    public boolean deleteProduct(Integer id) {
        return Optional.ofNullable(id)
                .filter(idFiltered -> repository.findById(idFiltered).isPresent())
                .map(idFiltered -> {
                    repository.deleteById(idFiltered);
                    return Boolean.TRUE;
                })
                .orElse(Boolean.FALSE);

    }

    public void deleteAllProducts() {
        repository.deleteAll();
    }

    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(Mapper::productEntityToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductByStatusAndEnteredByUser(ProductStatus status, String enteredByUser) {
        return repository.findByStatusAndEnteredByUser(status, enteredByUser)
                .stream()
                .map(Mapper::productEntityToDto)
                .collect(Collectors.toList());
    }
}
