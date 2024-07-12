package study.posthub.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.posthub.domain.member.service.impl.MainService;

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

        model.addAttribute("name", name);
        model.addAttribute("authority", authority);

        return "main";
    }
}