package com.rooney.james.util;

import org.springframework.security.core.Authentication;

/**
 * Created by jamesvrooney on 06/04/17.
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
