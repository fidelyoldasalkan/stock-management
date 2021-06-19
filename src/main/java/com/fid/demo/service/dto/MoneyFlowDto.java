package com.fid.demo.service.dto;

import com.fid.demo.entity.enums.MoneyFlowType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MoneyFlowDto extends BaseDto {

    private String accountName;
    private MoneyFlowType moneyFlowType;
    private Double amount;
    private LocalDate date;

    public MoneyFlowDto(Integer id, String accountName, MoneyFlowType moneyFlowType, Double amount, LocalDate date) {
        super(id);
        this.accountName = accountName;
        this.moneyFlowType = moneyFlowType;
        this.amount = amount;
        this.date = date;
    }

}
