package com.example.board.dto;

public class CommentCreateRequest {

    private Long memberId;
    private String content;

    public Long getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }
}
