package study.posthub.domain.member.controller;

import ch.qos.logback.core.testUtil.StringListAppender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.posthub.domain.member.dto.MemberInfo;
import study.posthub.domain.member.service.MemberService;
import study.posthub.domain.member.service.impl.MainService;

import java.io.IOException;

@Controller
public class MainController {

    private final MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        MemberInfo memberInfo = memberService.getAuthenticatedMemberInfo();
        model.addAttribute("memberInfo", memberInfo);
        return "main";
    }


}