package com.fid.demo.controller;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.entity.Account;
import com.fid.demo.service.impl.AccountService;
import com.fid.demo.util.GeneralResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account, AccountService> {

    public AccountController(AccountService accountService) {
        super(accountService);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<GeneralResponse> detail(@PathVariable Integer id) {
        return ResponseEntity.ok(GeneralResponseBuilder.success(baseService.detail(id)));
    }
}
