package com.fid.demo.service.impl;

import com.fid.demo.entity.Stock;
import com.fid.demo.repository.StockRepository;
import com.fid.demo.service.IStockService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {

    private final StockRepository stockRepository;

    @Override
    public Stock save(Stock entity) {
        return stockRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        stockRepository.deleteById(id);
    }

    @Override
    public List<Stock> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return stockRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
