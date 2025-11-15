package com.emegonza.stock.manager.model;

import com.emegonza.stock.manager.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public record ProductDto (String name,
                          String enteredByUser,
                          String enteredDate,
                          double buyingPrince,
                          double sellingPrice,
                          String lastModifiedDate,
                          String lastModifiedByUser,
                          ProductStatus status) { }
