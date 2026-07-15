package com.example.board.controller;

import com.example.board.domain.Comment;
import com.example.board.dto.request.CommentCreateRequest;
import com.example.board.dto.response.CommentResponse;
import com.example.board.dto.request.CommentUpdateRequest;
import com.example.board.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성 : POST /posts/{postId}
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> write(@PathVariable Long postId,
                                 @Valid @RequestBody CommentCreateRequest request) {
        Comment comment = commentService.write(
                postId,
                request.getMemberId(),
                request.getContent()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponse(comment));
    }

    // 댓글 수정 : PUT /comments/{id}
    @PutMapping("/comments/{id}")
    public CommentResponse update(@PathVariable Long id,
                                  @Valid @RequestBody CommentUpdateRequest request) {
        Comment comment = commentService.update(
                id,
                request.getContent()
        );
        return new CommentResponse(comment);
    }

    // 댓글 삭제 : DELETE /comments/{id}
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 댓글 목록 : GET /posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentResponse> findByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.findByPost(postId);
        return comments.stream()
                .map(CommentResponse::new)
                .toList();
    }
}
