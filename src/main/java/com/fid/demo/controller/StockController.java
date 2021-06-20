package com.fid.demo.controller;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.entity.Stock;
import com.fid.demo.service.impl.StockService;
import com.fid.demo.util.GeneralResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController extends BaseController<Stock, StockService> {
    public StockController(StockService stockService) {
        super(stockService);
    }

    @GetMapping("/listAll")
    public ResponseEntity<GeneralResponse> listAll() {
        return ResponseEntity.ok(GeneralResponseBuilder.success(baseService.findAll()));
    }
}
