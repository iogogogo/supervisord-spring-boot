package com.iogogogo.supervisord.aspect;

import com.iogogogo.supervisord.event.SupervisordEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tao.zeng on 2021/6/25.
 */
@Slf4j
@Aspect
@Configuration(proxyBeanMethods = false)
public class SupervisordAspect {

    private final ApplicationContext applicationContext;

    /**
     * Instantiates a new Supervisord aspect.
     *
     * @param applicationContext the application context
     */
    public SupervisordAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Supervisor.
     */
    @Pointcut("@annotation(com.iogogogo.supervisord.annotation.SupervisordEvent)")
    public void supervisord() {
    }


    /**
     * Do around object.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "supervisord()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        String method = String.join(".", signature.getDeclaringTypeName(), signature.getName());

        log.debug("CLASS_METHOD : {}", method);

        Object ret = joinPoint.proceed();

        // send Supervisord Event
        SupervisordEvent event = new SupervisordEvent(ret);
        event.setMethod(method);
        applicationContext.publishEvent(event);

        return ret;
    }
}
