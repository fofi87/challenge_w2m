package com.minData.W2m.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@Aspect
public class ExecutionTimeAspect {

    static Logger log = LoggerFactory.getLogger(ExecutionTimeAspect.class);
    private Long start;

    @Before("@within(com.minData.W2m.aspects.LogAnnotation)")
    public void beforeMethods(JoinPoint jp) throws Throwable {
        start = System.currentTimeMillis();
        log.info(new StringBuilder().append("Executing ").append(jp.getSignature()).toString());
    }

    @After("@within(com.minData.W2m.aspects.LogAnnotation)")
    public void afterMethods(JoinPoint jp) throws Throwable {
        long executionTime = System.currentTimeMillis() - this.start;
        log.info(new StringBuilder().append(jp.getSignature()).append(" executed in ").append(executionTime).append("ms").toString());
    }
}
