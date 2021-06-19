package com.fid.demo.entity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class StockHistory extends BaseEntity {

    private String stock;
    private LocalDate date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
}
