package com.fid.demo.service.impl;

import com.fid.demo.entity.Exchange;
import com.fid.demo.repository.ExchangeRepository;
import com.fid.demo.service.IExchangeService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService implements IExchangeService {

    private final ExchangeRepository exchangeRepository;

    @Override
    public List<Exchange> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return exchangeRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public Exchange save(Exchange entity) {
        return exchangeRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        exchangeRepository.softDelete(id);
    }
}
