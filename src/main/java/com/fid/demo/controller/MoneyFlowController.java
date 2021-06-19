package com.fid.demo.controller;

import com.fid.demo.entity.MoneyFlow;
import com.fid.demo.service.dto.MoneyFlowDto;
import com.fid.demo.service.impl.MoneyFlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/money-flow")
public class MoneyFlowController extends BaseController<MoneyFlow, MoneyFlowService> {

    public MoneyFlowController(MoneyFlowService moneyFlowService) {
        super(moneyFlowService);
    }

//    @GetMapping("/list2")
//    private ResponseEntity<List<MoneyFlowDto>> findAllByCUser2() {
//        return ResponseEntity.ok(baseService.findAllByCreatedBy2());
//    }
}
