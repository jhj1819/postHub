package study.posthub.domain.member.service.impl;

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
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Slf4j
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    private CustomOAuth2MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("memberInfo = {}", oAuth2User.getAttributes());

        OAuth2Response oAuth2Response = getOAuth2Response(userRequest, oAuth2User);
        if (oAuth2Response == null) {
            throw new OAuth2AuthenticationException("Unsupported provider: " + userRequest.getClientRegistration().getRegistrationId());
        }

        Member member = saveOrUpdateMember(oAuth2Response);
        return new CustomOAuth2Member(oAuth2Response, member.getAuthority().name());
    }

    private OAuth2Response getOAuth2Response(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if ("naver".equals(registrationId)) {
            return new NaverResponse(oAuth2User.getAttributes());
        }
        return null;
    }

    private Member saveOrUpdateMember(OAuth2Response oAuth2Response) {
        String email = oAuth2Response.getEmail();
        Optional<Member> existingMemberOptional = memberRepository.findByEmail(email);
        Member member;
        if (existingMemberOptional.isEmpty()) {
            member = new Member();
            member.setEmail(email);
            member.setNickname(oAuth2Response.getName());
            member.setAuthority(Authority.ADMIN);
            memberRepository.save(member);
        } else {
            member = existingMemberOptional.get();
            member.setEmail(email);
            member.setNickname(oAuth2Response.getName());
            memberRepository.save(member);
        }
        return member;
    }
}