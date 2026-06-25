package com.example.board;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성 : POST /posts?memberId=1&title=...&content=...
    @PostMapping("/posts")
    public PostResponse post(@RequestParam Long memberId,
                             @RequestParam String title,
                             @RequestParam String content) {
        Post post = postService.write(memberId, title, content);
        return new PostResponse(post);
    }

    // 게시글 조회 : GET /posts/{id}
    @GetMapping("/posts/{id}")
    public PostResponse findPost(@PathVariable Long id) {
        Post post = postService.findPost(id);
        return new PostResponse(post);
    }

    // 게시글 목록 : GET /posts
    @GetMapping("/posts")
    public List<PostResponse> findAll() {
        List<Post> posts = postService.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .toList();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        return e.getMessage();
    }
}