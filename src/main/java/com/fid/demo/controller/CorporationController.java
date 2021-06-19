package com.fid.demo.controller;

import com.fid.demo.entity.Corporation;
import com.fid.demo.service.impl.CorporationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporation")
public class CorporationController extends BaseController<Corporation, CorporationService> {
    public CorporationController(CorporationService baseService) {
        super(baseService);
    }
}
