package org.audit4j.integration.spring;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.audit4j.core.AuditManager;

/**
 * The Class AuditAspect.
 * 
 * <pre>
 * {@code
 *  
 *     <aop:aspectj-autoproxy>
 *         ...
 *         <aop:include name="auditAspect"/>
 *     </aop:aspectj-autoproxy>
 *     
 *     <bean id="auditAspect" class="org.audit4j.integration.spring.AuditAspect" />
 * 
 * }
 * </pre>
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 */
@Aspect
public class AuditAspect {

    /**
     * Execute public method.
     */
    @Pointcut("execution(@org.audit4j.core.annotation.Audit * *(..))")
    public void executeAuditMethod() {
    }

    /**
     * Execute audit class.
     */
    @Pointcut("within(@org.audit4j.core.annotation.Audit *)")
    public void executeAuditClass() {
    }

    /**
     * Audit Aspect
     * 
     * @param pjp
     *            the pjp
     * 
     * @throws Throwable
     *             the throwable
     */
    @Before("executeAuditMethod() || executeAuditClass()")
    public void audit(final JoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        AuditManager.getInstance().audit(pjp.getTarget().getClass(), method, pjp.getArgs());
    }
}
