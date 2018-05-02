package com.rasto.accommodationbookingsystem.security;

import com.rasto.accommodationbookingsystem.backend.data.Role;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserAuthenticationState {

    /**
     * @return true if user is authenticated
     */
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    /**
     * @return id of authenticated user
     */
    public Long getUserId() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    /**
     * @return true if logged user is admin, else false
     */
    public boolean isLoggedUserAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if ((auth == null) || (auth.getPrincipal() == null)) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities == null) {
            return false;
        }

        for (GrantedAuthority grantedAuthority : authorities) {
            if (Role.ADMIN.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

}
