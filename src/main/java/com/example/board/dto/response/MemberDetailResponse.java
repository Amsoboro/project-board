package com.example.board.dto.response;

import com.example.board.domain.Member;

public class MemberDetailResponse {

    private final Long id;
    private final String nickname;

    public MemberDetailResponse(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
