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

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String authority = null;

        String email = oAuth2Response.getEmail();

        //해당 emil 유저가 존재하는지 확인
        Optional<Member> existData = memberRepository.findByEmail(email);
        if (existData.get() == null){
            //생성
            Member member = new Member();
            member.setEmail(oAuth2Response.getEmail());
            member.setNickname(oAuth2Response.getName());
            member.setAuthority(Authority.ADMIN);

            memberRepository.save(member);

        }else{
            //업데이트
            authority = existData.get().getAuthority().name();
            existData.get().setEmail(oAuth2Response.getEmail());
            existData.get().setNickname(oAuth2Response.getName());
            memberRepository.save(existData.get());
        }



        return new CustomOAuth2Member(oAuth2Response, authority);
    }
}