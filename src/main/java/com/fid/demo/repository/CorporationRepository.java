package com.fid.demo.repository;

import com.fid.demo.entity.Corporation;

import java.util.List;

public interface CorporationRepository extends BaseRepository<Corporation> {
    List<Corporation> findAllByCreatedBy(Integer cUser);

}
