package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostCreateRequest {

    @NotNull
    private Long memberId;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
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
