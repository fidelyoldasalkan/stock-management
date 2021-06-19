package com.fid.demo.advice;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.util.GeneralResponseBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<GeneralResponse> handler(Exception ex) throws Exception {
        ex.printStackTrace();
        log.error(ex.getMessage());
        if (ex instanceof ExpiredJwtException) {
            throw ex;
        }
        return ResponseEntity.ok(GeneralResponseBuilder.error(ex));
    }
}
