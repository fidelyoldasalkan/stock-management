package com.fid.demo.service;

import com.fid.demo.entity.Account;
import com.fid.demo.service.dto.AccountDto;

public interface IAccountService extends IBaseService<Account> {

    AccountDto detail(Integer id);
}
