package com.fid.demo.service.impl;

import com.fid.demo.entity.Corporation;
import com.fid.demo.repository.CorporationRepository;
import com.fid.demo.service.ICorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorporationService implements ICorporationService {

    private final CorporationRepository corporationRepository;

    @Override
    public List<Corporation> findAllByCreatedBy() {
        return corporationRepository.findAll();
    }

    @Override
    public Corporation save(Corporation entity) {
        return corporationRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {

    }
}
