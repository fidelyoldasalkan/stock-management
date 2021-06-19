package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authorities")
@Data
@Where(clause = "is_active = true")
public class Authority extends BaseEntity {

    private String username;
    private String authority;
}
