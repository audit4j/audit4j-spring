package org.audit4j.integration.spring;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.audit4j.core.AuditManager;

@Aspect
public class AuditAspect2 {

    /**
     * Execute public method.
     */
    @Pointcut("execution(@org.audit4j.core.annotation.Audit * *(..))")
    public void executeAuditMethod() {
    }
    
    @Pointcut("within(@org.audit4j.core.annotation.Audit *)")
    public void executeAuditClass() {
    }

    /**
     * Log action.
     * 
     * @param pjp
     *            the pjp
     * @return the object
     * @throws Throwable
     *             the throwable
     */
    @Before("executeAuditMethod() || executeAuditClass()")
    public void logAction(final JoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        AuditManager.getInstance().audit(pjp.getClass(), method, pjp.getArgs());
    }
}
