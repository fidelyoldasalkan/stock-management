package com.fid.demo.service.dto;

import com.fid.demo.entity.enums.ExchangeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ExchangeDto extends BaseDateComparableDto {

    private Integer accountId;
    private String stock;
    private Integer lot;
    private Double price;
    private Double commission;
    private ExchangeType exchangeType;

    public ExchangeDto(Integer id, Integer accountId, String stock, Integer lot, Double price, LocalDate date, Double commission, ExchangeType exchangeType) {
        super(id, date, "Exchange");
        this.accountId = accountId;
        this.stock = stock;
        this.lot = lot;
        this.price = price;
        this.commission = commission;
        this.exchangeType  = exchangeType;
    }
}
