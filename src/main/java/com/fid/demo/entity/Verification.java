package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Where(clause = "is_active = true")
public class Verification extends BaseEntity {

    private String username;
    private Date expiredDateTime;
    private String verificationCode;
}
