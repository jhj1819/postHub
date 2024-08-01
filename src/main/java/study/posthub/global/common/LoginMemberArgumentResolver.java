package study.posthub.global.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import study.posthub.global.security.oauth2.dto.SessionMember;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 @LoginUser 어노테이션이 붙어있으면 true
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;

        // 파라미터 클래스 타입이 SessionUser.class면 true
        boolean isUserClass = SessionMember.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();

        if (session == null) {
            session.setAttribute("member", SessionMember.getAnonymousInstance());
        }

        return httpSession.getAttribute("member");
    }
}
