package com.fid.demo.repository;

import com.fid.demo.entity.Verification;

import java.util.List;

public interface VerificationRepository extends BaseRepository<Verification> {

    List<Verification> findAllByCreatedBy(Integer cUser);

}
