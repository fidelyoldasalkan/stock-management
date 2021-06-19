package com.fid.demo.entity;

import com.fid.demo.entity.enums.MoneyFlowType;
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
public class MoneyFlow extends BaseEntity {

    private Integer accountId;
    @Enumerated(EnumType.STRING)
    private MoneyFlowType moneyFlowType;
    private Double amount;
    private LocalDate date;
}
