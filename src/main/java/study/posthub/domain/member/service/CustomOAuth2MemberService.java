package study.posthub.domain.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import study.posthub.domain.member.dto.CustomOAuth2Member;
import study.posthub.domain.member.dto.NaverResponse;
import study.posthub.domain.member.dto.OAuth2Response;
import study.posthub.domain.member.entity.Authority;

@Service
@Slf4j
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("memberInfo = {}", oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String authority = Authority.ADMIN.name();

        return new CustomOAuth2Member(oAuth2Response, authority);
    }
}