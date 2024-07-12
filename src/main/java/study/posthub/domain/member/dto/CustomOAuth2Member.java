package study.posthub.domain.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2Member implements OAuth2User {

    private final OAuth2Response oAuth2Response;
    private final String authority;

    public CustomOAuth2Member(OAuth2Response oAuth2Response, String authority) {

        this.oAuth2Response = oAuth2Response;
        this.authority = authority;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return authority;
            }
        });

        return collection;
    }

    @Override
    public String getName() {

        return oAuth2Response.getName();
    }

    public String getUsername() { //id값을 강제로 특정하게 만들어준것,

        return oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
    }

    public String getEmail(){

        return oAuth2Response.getEmail();
    }
}