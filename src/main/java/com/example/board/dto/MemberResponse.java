package com.example.board.dto;

import com.example.board.domain.Member;

public class MemberResponse {
    private final Long id;

    public MemberResponse(Member member) {
        this.id = member.getId();
    }

    public Long getId() {
        return id;
    }
}
