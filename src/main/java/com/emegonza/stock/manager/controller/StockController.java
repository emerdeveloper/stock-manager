package com.emegonza.stock.manager.controller;

import com.emegonza.stock.manager.enums.ProductStatus;
import com.emegonza.stock.manager.model.ProductDto;
import com.emegonza.stock.manager.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stock/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    private final ProductService service;

    public StockController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
        return service.getProduct(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body(product))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> sveProduct(@RequestBody ProductDto product) {
        return service.saveProduct(product)
                .map(productSaved -> ResponseEntity.status(HttpStatus.CREATED).body(product))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto product) {
        return service.updateProduct(id, product)
                .map(productUpdated -> ResponseEntity.status(HttpStatus.OK).body(productUpdated))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        return service.deleteProduct(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts() {
        service.deleteAllProducts();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    //GET filters by fields ?status={status}&enteredByUser={modifiedBy}
    @GetMapping(params = {"status", "enteredByUser"})
    public ResponseEntity<List<ProductDto>> getProductByStatusAndEnteredByUser(
            @RequestParam("status") ProductStatus status,
            @RequestParam("enteredByUser") String enteredByUser) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductByStatusAndEnteredByUser(status, enteredByUser));
    }

    //GET select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy}
    @GetMapping(params = {"pageSize", "page", "sortBy"})
    public ResponseEntity<List<ProductDto>> getProductWithPage(@RequestParam("pageSize") int pageSize,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("sortBy") String sortBy) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductsWithPagination(pageable));
    }
}
