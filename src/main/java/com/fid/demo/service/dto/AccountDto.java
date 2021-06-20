package com.fid.demo.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AccountDto extends BaseDto {

    public AccountDto(Integer id, String name, Integer corporationId, Double commissionRate, String corporationName) {
        super(id);
        this.name = name;
        this.corporationId = corporationId;
        this.commissionRate = commissionRate;
        this.corporationName = corporationName;
    }

    private String name;
    private Integer corporationId;
    private Double commissionRate;
    private String corporationName;
    private Double amount;
    private List<BaseDateComparableDto> transactionList;

}
