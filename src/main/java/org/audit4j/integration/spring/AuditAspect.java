package org.audit4j.integration.spring;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.audit4j.core.AuditManager;



/**
 * The Class AuditAspect.
 * 
 * @author Janith Bandara
 */
@Aspect
public class AuditAspect {

    /** The log. */
    private final Log log = LogFactory.getLog(AuditAspect.class);

    /**
     * Execute public method.
     */
    @Pointcut(value = "execution(public * *(..))")
    public void executePublicMethod() {
    }

    /**
     * Log action.
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("executePublicMethod()")
    public Object logAction(final ProceedingJoinPoint pjp) throws Throwable {
    	AuditManager manager = AuditManager.getInstance();
    	Class<?> clazz = pjp.getClass();

    	MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    	Method method = methodSignature.getMethod();
    	
    	manager.auditWithAnnotation(clazz, method, pjp.getArgs());
        return pjp.proceed();
    }
}
