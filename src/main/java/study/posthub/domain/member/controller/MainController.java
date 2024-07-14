package study.posthub.domain.member.controller;

import ch.qos.logback.core.testUtil.StringListAppender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.posthub.domain.member.service.impl.MainService;

import java.io.IOException;

@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        String name = mainService.getAuthenticatedMemberName();
        String authority = mainService.getAuthenticatedMemberAuthoritiy();
        boolean isAuthenticated = mainService.getAuthenticatedMemberisAuthenticated();

        model.addAttribute("name", name);
        model.addAttribute("authority", authority);
        model.addAttribute("isAuthenticated", isAuthenticated);

        return "main";
    }

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 네이버 로그아웃 URL로 리다이렉트
        response.sendRedirect("https://nid.naver.com/nidlogin.logout?returl=");
    }
}