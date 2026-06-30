package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.dto.response.PageResponse;
import com.example.board.dto.request.PostCreateRequest;
import com.example.board.dto.response.PostResponse;
import com.example.board.dto.request.PostUpdateRequest;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성 : POST /posts
    @PostMapping("/posts")
    public PostResponse post(@Valid @RequestBody PostCreateRequest request) {
        Post post = postService.write(
                request.getMemberId(),
                request.getTitle(),
                request.getContent()
        );
        return new PostResponse(post);
    }

    // 게시글 조회 : GET /posts/{id}
    @GetMapping("/posts/{id}")
    public PostResponse findPost(@PathVariable Long id) {
        Post post = postService.findPost(id);
        return new PostResponse(post);
    }

    // 게시글 업데이트 : PUT /posts/{id}
    @PutMapping("/posts/{id}")
    public PostResponse update(@PathVariable Long id,
                               @Valid @RequestBody PostUpdateRequest request) {
        Post post = postService.update(
                id,
                request.getTitle(),
                request.getContent()
        );
        return new PostResponse(post);
    }

    // 게시글 삭제 : DELETE /posts/{id}
    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    // 게시글 목록 : GET /posts
    @GetMapping("/posts")
    public PageResponse<PostResponse> findAll(Pageable pageable) {
        Page<PostResponse> posts = postService.findAll(pageable)
                .map(PostResponse::new);
        return new PageResponse<>(posts);
    }
}