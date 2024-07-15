package study.posthub.global.security.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import study.posthub.domain.member.entity.Authority;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CustomOAuth2Member implements OAuth2User {

    private final OAuth2Response oAuth2Response;
//    private final Authority authority;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Authority.USER.name()));
    }

    @Override
    public String getName() {
        return oAuth2Response.getName();
    }
}