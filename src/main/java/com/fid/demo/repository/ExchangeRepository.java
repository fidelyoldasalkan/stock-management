package com.fid.demo.repository;

import com.fid.demo.entity.Exchange;
import com.fid.demo.service.dto.ExchangeDto;
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

    @Query("select new com.fid.demo.service.dto.ExchangeDto(e.id, e.accountId, e.stock, e.lot, e.price, e.date, e.commission, e.exchangeType) from Exchange e where e.accountId = :accountId and e.isActive = true order by e.date desc, e.id")
    List<ExchangeDto> findAllByAccountId(@Param("accountId") Integer accountId);

}
