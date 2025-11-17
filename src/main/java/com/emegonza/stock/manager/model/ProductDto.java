package com.emegonza.stock.manager.model;

import com.emegonza.stock.manager.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public record ProductDto (Integer id,
                          String name,
                          String enteredByUser,
                          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-M-yyyy hh:mm:ss a")
                          Date enteredDate,
                          double buyingPrince,
                          double sellingPrice,
                          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-M-yyyy hh:mm:ss a")
                          Date lastModifiedDate,
                          String lastModifiedByUser,
                          ProductStatus status) { }
