package com.fid.demo.service.impl;

import com.fid.demo.entity.Authority;
import com.fid.demo.repository.AuthorityRepository;
import com.fid.demo.service.IAuthorityService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService implements IAuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authorityRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public Authority save(Authority entity) {
        return entity;
    }

    @Override
    public void delete(Integer id) {

    }
}
