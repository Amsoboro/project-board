package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateRequest {

    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }
}
