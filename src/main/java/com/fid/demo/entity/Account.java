package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause = "is_active = true")
public class Account extends BaseEntity {

    private String name;
    private Integer corporationId;
    private Double commissionRate;
    private Boolean isDefault;
}
