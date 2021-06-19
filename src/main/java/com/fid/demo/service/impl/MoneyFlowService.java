package com.fid.demo.service.impl;

import com.fid.demo.entity.MoneyFlow;
import com.fid.demo.repository.MoneyFlowRepository;
import com.fid.demo.service.IMoneyFlowService;
import com.fid.demo.service.dto.MoneyFlowDto;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyFlowService implements IMoneyFlowService {

    private final MoneyFlowRepository moneyFlowRepository;

    @Override
    public List<?> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return moneyFlowRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public MoneyFlow save(MoneyFlow entity) {
        return moneyFlowRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        moneyFlowRepository.softDelete(id);
    }

//    @Override
//    public List<MoneyFlowDto> findAllByCreatedBy2() {
//        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return moneyFlowRepository.findAllByCreatedBy(simpleUser.getId());
//    }
}
