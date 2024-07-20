package study.posthub.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

//    @GetMapping("/")
//    public String mainPage(@LoginMember SessionMember member, Model model) {
//
//        log.info("member: {}", member);
//        model.addAttribute("user", member);
//        return "main";
//    }
//
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @GetMapping("/")
//    public String mainPage() {
//        return "index";
//    }
}