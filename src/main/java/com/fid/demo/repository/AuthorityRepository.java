package com.fid.demo.repository;

import com.fid.demo.entity.Account;
import com.fid.demo.entity.Authority;

import java.util.List;

public interface AuthorityRepository extends BaseRepository<Authority> {

    List<Authority> findAllByCreatedBy(Integer cUser);

}
