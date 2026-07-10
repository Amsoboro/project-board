package com.example.board.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super("존재하지 않는 회원입니다 : " + id);
    }
}
