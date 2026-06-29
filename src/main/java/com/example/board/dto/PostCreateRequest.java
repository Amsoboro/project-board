package com.example.board.dto;

public class PostCreateRequest {

    private Long memberId;
    private String title;
    private String content;

    public Long getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
