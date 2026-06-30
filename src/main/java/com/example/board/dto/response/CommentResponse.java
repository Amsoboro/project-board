package com.example.board.dto.response;

import com.example.board.domain.Comment;

public class CommentResponse {
    private final Long id;
    private final String content;
    private final String authorNickname;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorNickname = comment.getAuthor().getNickname();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }
}
