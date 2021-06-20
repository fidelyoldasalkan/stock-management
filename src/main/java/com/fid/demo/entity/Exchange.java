package com.fid.demo.entity;

import com.fid.demo.entity.enums.ExchangeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause = "is_active = true")
public class Exchange extends BaseEntity {

    private Integer accountId;
    private String stock;
    private Integer lot;
    private Double price;
    private LocalDate date;
    private Double commission;
    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;
}
