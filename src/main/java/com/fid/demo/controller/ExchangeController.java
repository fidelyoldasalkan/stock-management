package com.fid.demo.controller;

import com.fid.demo.entity.Exchange;
import com.fid.demo.service.IExchangeService;
import com.fid.demo.service.impl.ExchangeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController extends BaseController<Exchange, ExchangeService> {

    public ExchangeController(ExchangeService exchangeService) {
        super(exchangeService);
    }
}