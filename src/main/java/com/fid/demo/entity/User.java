package com.fid.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@Where(clause = "is_active = true")

public class User extends BaseEntity {

    private String name;
    private String surname;
    private String phone;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean isVerification;
}
