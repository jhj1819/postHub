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
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getAuthenticatedMemberAuthoritiy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authenticationInfo = {}", authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (!authorities.isEmpty()) {
            return authorities.iterator().next().getAuthority();
        }

        return null;
    }
}