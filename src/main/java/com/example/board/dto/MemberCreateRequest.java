package com.example.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberCreateRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nickname;

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
