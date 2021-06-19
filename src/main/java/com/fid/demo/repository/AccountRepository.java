package com.fid.demo.repository;

import com.fid.demo.entity.Account;
import com.fid.demo.service.dto.AccountDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account> {

    @Modifying
    @Query("update Account a set a.isActive = false where a.id = :id")
    void softDelete(@Param("id") Integer id);

//    List<Account> findAllByCreatedBy(Integer cUser);

    @Query("select new com.fid.demo.service.dto.AccountDto(a.id, a.name, a.corporationId, a.commissionRate, c.name) from Account a, Corporation c where a.corporationId = c.id and a.isActive = 1 and c.isActive = 1")
    List<AccountDto> findAllByCreatedBy(@Param("createdBy") Integer createdBy);

}
