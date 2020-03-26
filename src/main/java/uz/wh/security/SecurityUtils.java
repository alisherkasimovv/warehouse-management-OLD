package uz.wh.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.wh.security.jwt.JwtUser;

public final class SecurityUtils {

    public SecurityUtils() {
    }

    public static int getUserId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof JwtUser){
                JwtUser springSecurityUser = (JwtUser) authentication.getPrincipal();
                return springSecurityUser.getId();
            }
        }
        return 0;
    }
}
