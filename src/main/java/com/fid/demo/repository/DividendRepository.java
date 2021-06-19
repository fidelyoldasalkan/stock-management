package com.fid.demo.repository;

import com.fid.demo.entity.Corporation;
import com.fid.demo.entity.Dividend;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DividendRepository extends BaseRepository<Dividend> {

    @Modifying
    @Query("update Dividend d set d.isActive = false where d.id = :id")
    void softDelete(@Param("id") Integer id);

    @Modifying
    @Query("update Dividend d set d.isActive = false where d.accountId = :accountId")
    void softDeleteByAccountId(@Param("accountId") Integer accountId);

    List<Dividend> findAllByCreatedBy(Integer cUser);

}
