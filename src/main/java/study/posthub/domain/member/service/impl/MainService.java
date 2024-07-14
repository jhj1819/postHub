package study.posthub.domain.member.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class MainService {

    public String getAuthenticatedMemberName() {
        Authentication authentication = getAuthentication();
        return authentication != null ? authentication.getName() : "Anonymous";
    }

    public String getAuthenticatedMemberAuthoritiy() {
        Collection<? extends GrantedAuthority> authorities = getAuthentication().getAuthorities();

        if (!authorities.isEmpty()) {
            return authorities.iterator().next().getAuthority();
        }

        return null;
    }

    public boolean getAuthenticatedMemberisAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}