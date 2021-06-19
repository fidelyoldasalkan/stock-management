package com.fid.demo.controller;

import com.fid.demo.entity.Account;
import com.fid.demo.service.impl.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account, AccountService> {

    public AccountController(AccountService accountService) {
        super(accountService);
    }
}
