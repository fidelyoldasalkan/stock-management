package com.fid.demo.service.impl;

import com.fid.demo.entity.Account;
import com.fid.demo.entity.enums.MoneyFlowType;
import com.fid.demo.repository.AccountRepository;
import com.fid.demo.repository.DividendRepository;
import com.fid.demo.repository.ExchangeRepository;
import com.fid.demo.repository.MoneyFlowRepository;
import com.fid.demo.service.IAccountService;
import com.fid.demo.service.dto.AccountDto;
import com.fid.demo.service.dto.BaseDateComparableDto;
import com.fid.demo.service.dto.MoneyFlowDto;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final MoneyFlowRepository moneyFlowRepository;
    private final DividendRepository dividendRepository;
    private final ExchangeRepository exchangeRepository;

    @Override
    @Transactional
    public List<AccountDto> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AccountDto> accounts = accountRepository.findAllByCreatedBy(simpleUser.getId());

        accounts.forEach(accountDto -> {
            List<MoneyFlowDto> moneyFlows = moneyFlowRepository.findAllByAccountIdAndCreatedBy(accountDto.getId(), simpleUser.getId());
            double depositSum = moneyFlows.stream().filter(mf -> mf.getMoneyFlowType() == MoneyFlowType.DEPOSIT).mapToDouble(MoneyFlowDto::getAmount).sum();
            double withdrawSum = moneyFlows.stream().filter(mf -> mf.getMoneyFlowType() == MoneyFlowType.WITHDRAW).mapToDouble(MoneyFlowDto::getAmount).sum();
//            accountDto.setMoneyFlowList(moneyFlows);
            // TODO hisse senetlerinin gün sonu kapanış değerleri bulunup hesaplanmadı
            accountDto.setAmount(depositSum - withdrawSum);
        });

        return accounts;
    }

    @Override
    public Account save(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        moneyFlowRepository.softDeleteByAccountId(id);
        dividendRepository.softDeleteByAccountId(id);
        exchangeRepository.softDeleteByAccountId(id);
        accountRepository.softDelete(id);
    }

    @Override
    public AccountDto detail(Integer id) {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDto account = accountRepository.findByCreatedBy(id);
        List<BaseDateComparableDto> list = new ArrayList<>(dividendRepository.findAllByAccountId(account.getId()));
        list.addAll(exchangeRepository.findAllByAccountId(account.getId()));
        List<MoneyFlowDto> mfList = moneyFlowRepository.findAllByAccountIdAndCreatedBy(account.getId(), simpleUser.getId());
        mfList.forEach(mf -> mf.setAccountName(account.getName()));
        list.addAll(mfList);
        Collections.sort(list);
        account.setTransactionList(list);
        return account;
    }
}
