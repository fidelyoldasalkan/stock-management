package com.fid.demo.aspect;

import com.fid.demo.aspect.annotation.CreatedByFilter;
import com.fid.demo.entity.User;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
//@Component
@RequiredArgsConstructor
public class FilterCreatedByAspect {

    private final SessionFactory sessionFactory;

    @Around("@annotation(com.fid.demo.aspect.annotation.CreatedByFilter)")
//    @Around("execution(* com.fid.demo.service.impl.*.*(..))")
    @Transactional
    public Object filter(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        CreatedByFilter createdByFilter = method.getAnnotation(CreatedByFilter.class);

        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession
                .enableFilter("createdBy")
                .setParameter("cUser", simpleUser.getId());

        List<User> list = currentSession.createQuery("select t from " + createdByFilter.value() + " t ", User.class).getResultList();

        list.forEach(u -> System.out.println(u.getUsername()));
        return list;
    }
}
