package com.fid.demo.service.impl;

import com.fid.demo.entity.Verification;
import com.fid.demo.repository.VerificationRepository;
import com.fid.demo.service.IVerificationService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {

    private final VerificationRepository verificationRepository;

    @Override
    public List<Verification> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return verificationRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public Verification save(Verification entity) {
        return verificationRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {

    }

}
