package com.fid.demo.service.impl;

import com.fid.demo.entity.Dividend;
import com.fid.demo.repository.DividendRepository;
import com.fid.demo.service.IDividendService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DividendService implements IDividendService {

    private final DividendRepository dividendRepository;

    @Override
    public List<Dividend> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dividendRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public Dividend save(Dividend entity) {
        return dividendRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dividendRepository.softDelete(id);
    }
}
