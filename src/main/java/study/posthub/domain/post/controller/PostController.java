package study.posthub.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.posthub.domain.comment.dto.CommentViewResponse;
import study.posthub.domain.comment.service.CommentService;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Keyword;
import study.posthub.domain.post.service.PostService;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/")
    public String getAllPosts(Model model,
                              @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostViewResponse> posts = postService.getAllPosts(pageable);

        model.addAttribute("posts", posts);
        return "index";
    }

    /* SEARCH */
    @GetMapping("/search")
    public String getPostsByTitle(Model model,
                                  @RequestParam(defaultValue = "TITLE") Keyword keyword,
                                  @RequestParam String query,
                                  @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostViewResponse> posts = postService.getPostsByKeyword(keyword, query, pageable);

        log.info("pageNumber = {}", pageable.getPageNumber());
        log.info("pageSize = {}", pageable.getPageSize());
        log.info("offset = {}", pageable.getOffset());
        log.info("totalElements = {}", posts.getTotalElements());
        log.info("totalPages = {}", posts.getTotalPages());

        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/new-post")
    public String newPost(@RequestParam(required = false) Long id,
                          Model model) {

        if (id == null) {
            model.addAttribute("posts", null);
        } else {
            PostViewResponse postViewResponse = postService.getPostById(id);
            model.addAttribute("posts", postViewResponse);
        }

        return "newPost";
    }

    @GetMapping("/post/{id}")
    public String getPostById(@LoginMember SessionMember member, @PathVariable Long id, Model model) {  //@PathVariable : {id}를 변수로 인식시킴
        PostViewResponse postViewResponse = postService.getPostById(id);
        Page<CommentViewResponse> comments = commentService.getCommentsByPostId(id, Pageable.unpaged());
        model.addAttribute("post", postViewResponse);
        model.addAttribute("comments", comments);
        model.addAttribute("member", Objects.requireNonNullElseGet(member, SessionMember::getAnonymousInstance));
        return "postDetail";
    }
}
