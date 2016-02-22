/*
 * Copyright (c) 2014-2016 Janith Bandara, This source is a part of
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

import javax.servlet.ServletRequest;

import org.audit4j.core.MetaData;
import org.audit4j.core.util.annotation.Beeta;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Abstraction for Spring metadata implementations.
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 * 
 * @since 2.4.1
 */
@Beeta
public abstract class SpringWebAuditMetadata implements MetaData {

    /** default actor name for unidentified actor. */
    protected String undefienedActorName = "unedentified";

    /** Default name for unidentified origin. */
    protected String undefienedOriginName = "unedentified";

    protected boolean captureUserAgent = false;

    /**
     * {@inheritDoc}
     * 
     * @see org.audit4j.core.MetaData#getOrigin()
     * 
     */
    @Override
    public String getOrigin() {
        ServletRequest request = (ServletRequest) ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        try {
            return request.getRemoteAddr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return undefienedOriginName;
    }

    /**
     * Allows to set the undefined actor name.
     * 
     * @param undefinedActorName
     *            the new undefined actor name
     */
    public void setUndefienedActorName(String undefinedActorName) {
        this.undefienedActorName = undefinedActorName;
    }

    /**
     * Allows to set the undefined origin name.
     * 
     * @param undefinedOriginName
     *            the new undefined origin name
     */
    public void setUndefienedOriginName(String undefinedOriginName) {
        this.undefienedOriginName = undefinedOriginName;
    }

    public void setCaptureUserAgent(boolean captureUserAgent) {
        this.captureUserAgent = captureUserAgent;
    }
}
