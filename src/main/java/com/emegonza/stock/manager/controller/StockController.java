package com.emegonza.stock.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stock/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

}
