package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause = "is_active = true")
public class Stock extends BaseEntity {

    private String shortCode;
    private String name;
}
