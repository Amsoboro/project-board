package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostUpdateRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
