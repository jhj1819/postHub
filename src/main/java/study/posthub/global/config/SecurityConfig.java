package study.posthub.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import study.posthub.domain.member.service.impl.CustomOAuth2MemberService;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2MemberService customOAuth2MemberService;

    public  SecurityConfig(CustomOAuth2MemberService customOAuth2MemberService){

        this.customOAuth2MemberService = customOAuth2MemberService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login((outh2) -> outh2
                        .userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(customOAuth2MemberService)))
                .authorizeHttpRequests(req -> req
                        .anyRequest().authenticated())
                .build();
    }
}
