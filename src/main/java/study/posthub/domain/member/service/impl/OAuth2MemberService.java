package study.posthub.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import study.posthub.domain.member.dto.OAuth2Member;
import study.posthub.domain.member.dto.OAuth2Response;
import study.posthub.domain.member.dto.impl.GoogleResponse;
import study.posthub.domain.member.dto.impl.NaverResponse;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.repository.MemberRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 사용자 정보 가져오기
        OAuth2User user = super.loadUser(userRequest);
        log.info("memberInfo = {}", user.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if (provider.equals("naver")) {
            oAuth2Response = new NaverResponse(user.getAttributes());
        } else if (provider.equals("google")) {
            oAuth2Response = new GoogleResponse(user.getAttributes());
        } else {
            return null;
        }

        Authority authority = Authority.USER;
        return new CustomOAuth2Member(oAuth2Response, authority);
    }
}