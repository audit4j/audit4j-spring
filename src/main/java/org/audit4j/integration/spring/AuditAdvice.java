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

import org.audit4j.core.AuditManager;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * The Class AuditAdvice.
 * 
 * <p>
 * </p>
 * Usage:
 * 
 * <pre>
 * {@code
 * <bean id="auditAdvice" class="org.audit4j.integration.spring.AuditAdvice" />
 * 
 * <bean id="serviceClass" class="com.xyz.myapp.service.ServiceImpl">
 *      <!-- properties here -->
 * </bean>
 * 
 * <bean id="serviceImplProxy" 
 *                  class="org.springframework.aop.framework.ProxyFactoryBean">
 *         <property name="target" ref="serviceClass" />
 *  
 *         <property name="interceptorNames">
 *             <list>
 *                 <value>auditAdvice</value>
 *             </list>
 *         </property>
 *   </bean>
 * }
 * </pre>
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 */
public class AuditAdvice implements MethodBeforeAdvice {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method
     * , java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(final Method method, final Object[] arg1, final Object arg2) throws Throwable {
        AuditManager manager = AuditManager.getInstance();
        manager.audit(method.getClass(), method, arg1);
    }
}
