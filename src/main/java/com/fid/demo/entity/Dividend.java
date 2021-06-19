package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause = "is_active = true")
public class Dividend extends BaseEntity {

    private Integer accountId;
    private LocalDate date;
    private String stock;
    private Integer lot;
    private Double amount;
}
