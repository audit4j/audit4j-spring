/*
 * Copyright 2014 Janith Bandara, This source is a part of Audit4j - 
 * An open-source audit platform for Enterprise java platform.
 * http://mechanizedspace.com/audit4j
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
    	
    	manager.audit(clazz, method, pjp.getArgs());
        return pjp.proceed();
    }
}
