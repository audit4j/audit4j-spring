package org.audit4j.integration.spring;

import org.audit4j.core.util.annotation.Beeta;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Default Metadata class to capture actor and origin information from spring
 * application context using session attributes and servlet requests.
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 * 
 * @since 2.4.1
 */
@Beeta
public class WebSessionAuditMetaData extends SpringWebAuditMetadata {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8565254148203328955L;

    /** default session attribute name. */
    private String actorSessionAttribute = "userName";

    /**
     * {@inheritDoc}
     * 
     * @see org.audit4j.core.MetaData#getActor()
     * 
     */
    @Override
    public String getActor() {
        String actor = (String) RequestContextHolder.currentRequestAttributes().getAttribute(actorSessionAttribute,
                RequestAttributes.SCOPE_SESSION);
        if (actor != null) {
            return actor;
        }
        return undefienedActorName;
    }

    /**
     * Sets the actor session attribute.
     * 
     * @param actorSessionAttribute
     *            the new actor session attribute
     */
    public void setActorSessionAttribute(String actorSessionAttribute) {
        this.actorSessionAttribute = actorSessionAttribute;
    }

}
