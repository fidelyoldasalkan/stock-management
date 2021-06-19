package com.fid.demo.repository;

import com.fid.demo.entity.Exchange;
import com.fid.demo.entity.MoneyFlow;
import com.fid.demo.service.dto.MoneyFlowDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoneyFlowRepository extends BaseRepository<MoneyFlow> {

    @Modifying
    @Query("update MoneyFlow m set m.isActive = false where m.id = :id")
    void softDelete(@Param("id") Integer id);

    @Modifying
    @Query("update MoneyFlow m set m.isActive = false where m.accountId = :accountId")
    void softDeleteByAccountId(@Param("accountId") Integer accountId);

//    List<MoneyFlow> findAllByCreatedBy(Integer cUser);

    @Query("select new com.fid.demo.service.dto.MoneyFlowDto(mf.id, a.name, mf.moneyFlowType, mf.amount, mf.date) from MoneyFlow mf, Account a where mf.accountId = a.id and mf.createdBy = :createdBy and mf.isActive = 1 and a.isActive = 1")
    List<MoneyFlowDto> findAllByCreatedBy(@Param("createdBy") Integer createdBy);

    @Query("select new com.fid.demo.service.dto.MoneyFlowDto(mf.id, '', mf.moneyFlowType, mf.amount, mf.date) from MoneyFlow mf where mf.accountId = :accountId and mf.createdBy = :createdBy and mf.isActive = 1")
    List<MoneyFlowDto> findAllByAccountIdAndCreatedBy(@Param("accountId") Integer accountId,@Param("createdBy") Integer createdBy);
}
