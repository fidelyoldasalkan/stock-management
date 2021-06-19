package com.fid.demo.repository;

import com.fid.demo.entity.Stock;
import com.fid.demo.entity.User;

import java.util.List;

public interface StockRepository extends BaseRepository<Stock> {

    List<Stock> findAllByCreatedBy(Integer cUser);

}
