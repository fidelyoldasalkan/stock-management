package com.fid.demo.repository;

import com.fid.demo.entity.Dividend;
import com.fid.demo.entity.Exchange;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExchangeRepository extends BaseRepository<Exchange> {

    @Modifying
    @Query("update Exchange e set e.isActive = false where e.id = :id")
    void softDelete(@Param("id") Integer id);

    List<Exchange> findAllByCreatedBy(Integer cUser);

    @Modifying
    @Query("update Exchange e set e.isActive = false where e.accountId = :accountId")
    void softDeleteByAccountId(@Param("accountId") Integer accountId);
}
