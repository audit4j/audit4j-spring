package org.audit4j.integration.spring;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.audit4j.core.AuditManager;
import org.springframework.aop.MethodBeforeAdvice;


/**
 * The Class AuditAdvice.
 * 
 * @author Janith Bandara
 */
public class AuditAdvice implements MethodBeforeAdvice {

    /** The log. */
    private final Log log = LogFactory.getLog(AuditAdvice.class);

    /* (non-Javadoc)
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(final Method method, final Object[] arg1, final Object arg2) throws Throwable {

        if (log.isDebugEnabled())
            log.debug("Calling Audit advice before " + method.getName());

        AuditManager manager = AuditManager.getInstance();
        manager.auditWithAnnotation(method.getClass(), method, arg1);
    }
}
