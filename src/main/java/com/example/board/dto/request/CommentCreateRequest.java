package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentCreateRequest {

    @NotNull
    private Long memberId;

    @NotBlank
    private String content;

    public Long getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }
}
