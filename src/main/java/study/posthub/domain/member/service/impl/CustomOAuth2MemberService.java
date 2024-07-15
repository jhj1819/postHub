package study.posthub.domain.member.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import study.posthub.domain.member.dto.CustomOAuth2Member;
import study.posthub.domain.member.dto.OAuth2Response;
import study.posthub.domain.member.dto.impl.NaverResponse;
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("memberInfo = {}", oAuth2User.getAttributes());

        OAuth2Response oAuth2Response = getOAuth2Response(userRequest, oAuth2User)
                .orElseThrow(() -> new OAuth2AuthenticationException(
                        "Unsupported provider: " + userRequest.getClientRegistration().getRegistrationId()));

        Member member = saveOrUpdateMember(oAuth2Response);
        session.setAttribute("member", member);

        return new CustomOAuth2Member(oAuth2Response, member.getAuthority());
    }

    private Optional<OAuth2Response> getOAuth2Response(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if ("naver".equals(registrationId)) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else {
            oAuth2Response = null;
        }

        return Optional.ofNullable(oAuth2Response);
    }

    private Member saveOrUpdateMember(OAuth2Response oAuth2Response) {
        // 객체 2개 생기는지 테스트하기
        String email = oAuth2Response.getEmail();
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.getInstance(oAuth2Response));

        member.updateByRegister(oAuth2Response);
        memberRepository.save(member);

        return member;
    }
}