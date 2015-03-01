/*
 * Copyright 2014 Janith Bandara, This source is a part of 
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

import java.util.List;

import org.audit4j.core.AuditManager;
import org.audit4j.core.Configuration;
import org.audit4j.core.MetaData;
import org.audit4j.core.filter.AuditEventFilter;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.layout.Layout;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * The Class SpringAudit4jConfig.
 *
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 */
public class SpringAudit4jConfig implements InitializingBean, DisposableBean {

    /** The layout. */
    private Layout layout;

    /** The handlers. */
    private List<Handler> handlers;

    /** The meta data. */
    private MetaData metaData;
    
    /** The filters. */
    private List<AuditEventFilter> filters;
    
    /** The options. */
    private String options;
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = Configuration.INSTANCE;
        configuration.setLayout(layout);
        configuration.setHandlers(handlers);
        configuration.setMetaData(metaData);
        configuration.setFilters(filters);
        configuration.setOptions(options);
        AuditManager.startWithConfiguration(configuration);
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        AuditManager.getInstance().shutdown();
    }

    /**
     * Sets the layout.
     *
     * @param layout the new layout
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * Sets the meta data.
     *
     * @param metaData the new meta data
     */
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    /**
     * Sets the handlers.
     *
     * @param handlers the new handlers
     */
    public void setHandlers(List<Handler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Sets the filters.
     *
     * @param filters the new filters
     */
    public void setFilters(List<AuditEventFilter> filters) {
        this.filters = filters;
    }

    /**
     * Sets the options.
     *
     * @param options the new options
     */
    public void setOptions(String options) {
        this.options = options;
    }
}
