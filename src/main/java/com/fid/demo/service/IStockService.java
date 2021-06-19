package com.fid.demo.service;

import com.fid.demo.entity.Stock;

import java.util.List;

public interface IStockService extends IBaseService<Stock>{

    List<Stock> findAll();
}
