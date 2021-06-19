package com.fid.demo.controller;

import com.fid.demo.entity.Dividend;
import com.fid.demo.service.IDividendService;
import com.fid.demo.service.impl.DividendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dividend")
public class DividendController extends BaseController<Dividend, DividendService> {

    public DividendController(DividendService dividendService) {
        super(dividendService);
    }
}
