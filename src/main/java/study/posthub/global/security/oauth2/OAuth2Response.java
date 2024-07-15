package study.posthub.global.security.oauth2;

public interface OAuth2Response {

    // 제공자 (naver, google, kakao 등)
    String getProvider();

    // 제공자에서 발급해주는 아이디
    String getProviderId();

    // 사용자 정보
    String getEmail();
    String getName();
}