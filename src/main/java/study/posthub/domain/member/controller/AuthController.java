package study.posthub.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(Model model, MemberRequest memberRequest) {
        model.addAttribute("memberRequest", memberRequest);

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model, MemberRequest memberRequest) {
        model.addAttribute("memberRequest", memberRequest);

        return "register";
    }

    @PostMapping("/register")
    public String register(MemberRequest memberRequest) {
        memberService.register(memberRequest);

        return "redirect:/";
    }
}