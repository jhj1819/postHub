package study.posthub.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.posthub.domain.member.service.MemberService;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;


    @GetMapping("/")
    public String getAllPosts(Model model) {
        List<Post> posts  = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/new-post")
    public String newPost(@RequestParam(required = false) Long id, Model model) {

        if (id == null){
            model.addAttribute("post", new PostViewResponse());
        }else{
            Post post = postService.getPostById(id);
            model.addAttribute("post", new PostViewResponse(post));
        }

        return "newPost";
    }


    @GetMapping("/post/{id}")
    public String getPostById(@PathVariable Long id, Model model) {  //@PathVariable : {id}를 변수로 인식시킴
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "boardDetail";
    }



}
