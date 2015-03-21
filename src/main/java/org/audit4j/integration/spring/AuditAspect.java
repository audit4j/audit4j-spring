/*
 * Copyright (c) 2014-2015 Janith Bandara, This source is a part of
 * Audit4j - An open source auditing framework.
 * http://audit4j.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
