package com.fid.demo.util;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.exception.StockManagementException;

public class GeneralResponseBuilder {

    public static GeneralResponse success(Object data) {
        return new GeneralResponse("SUCCESS", null, data);
    }

    public static GeneralResponse error(Exception e) {
        if (e instanceof StockManagementException) {
            StockManagementException ex = (StockManagementException) e;
            return new GeneralResponse("ERROR", ex.getMessageCode(), null);
        } else {
            return new GeneralResponse("ERROR", e.getMessage(), null);
        }
    }
}
