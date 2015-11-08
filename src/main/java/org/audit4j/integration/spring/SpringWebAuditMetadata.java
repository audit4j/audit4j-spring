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
 * @since 2.4.1
 */
@Beeta
public abstract class SpringWebAuditMetadata implements MetaData {

    /** default actor name for unidentified actor. */
    String undefienedActorName = "unedentified";

    /** Default name for unidentified origin. */
    String undefienedOriginName = "unedentified";

    /**
     * {@inheritDoc}
     * 
     * @see org.audit4j.core.MetaData#getOrigin()
     * 
     */
    @Override
    public String getOrigin() {
        try {
            return ((ServletRequest) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest()).getRemoteAddr();
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
}
