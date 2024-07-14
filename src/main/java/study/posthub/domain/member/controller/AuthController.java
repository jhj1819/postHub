package study.posthub.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class AuthController {
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