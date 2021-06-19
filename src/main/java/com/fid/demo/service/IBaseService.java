package com.fid.demo.service;

import com.fid.demo.entity.BaseEntity;

import java.util.List;

public interface IBaseService<T extends BaseEntity> {

    T save(T entity);
    void delete(Integer id);
    List<?> findAllByCreatedBy();

}
