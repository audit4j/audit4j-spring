package org.audit4j.integration.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Default Metadata class to capture actor and origin information from spring
 * application context using Spring security and servlet requests.
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 * 
 * @since 2.4.1
 */
public class SpringSecurityWebAuditMetaData extends SpringWebAuditMetadata {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7243065407615627372L;

    /**
     * {@inheritDoc}
     * 
     * @see org.audit4j.core.MetaData#getActor()
     * 
     */
    @Override
    public String getActor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return undefienedActorName;
    }
}
