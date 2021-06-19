package com.fid.demo.aspect;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.util.GeneralResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
//@Component
public class ControllerReturnAspect {

    @Around("execution(* com.fid.demo.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            log.info(pjp.getSignature() + " working");
            return pjp.proceed();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(GeneralResponseBuilder.error(e));
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            return ResponseEntity.ok(GeneralResponseBuilder.error(new RuntimeException("UNEXPECTED THROWABLE")));
        }
    }
}
