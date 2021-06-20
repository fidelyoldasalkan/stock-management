package com.fid.demo.service.dto;

import com.fid.demo.entity.enums.MoneyFlowType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MoneyFlowDto extends BaseDateComparableDto {

    private String accountName;
    private MoneyFlowType moneyFlowType;
    private Double amount;

    public MoneyFlowDto(Integer id, String accountName, MoneyFlowType moneyFlowType, Double amount, LocalDate date) {
        super(id, date, "MoneyFlow");
        this.accountName = accountName;
        this.moneyFlowType = moneyFlowType;
        this.amount = amount;
    }

}
