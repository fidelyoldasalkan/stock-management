package com.fid.demo.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DividendDto extends BaseDateComparableDto {

    private Integer accountId;
    private String stock;
    private Integer lot;
    private Double amount;

    public DividendDto(Integer id, Integer accountId, LocalDate date, String stock, Integer lot, Double amount) {
        super(id, date, "Dividend");
        this.accountId = accountId;
        this.stock = stock;
        this.lot = lot;
        this.amount = amount;
    }
}
