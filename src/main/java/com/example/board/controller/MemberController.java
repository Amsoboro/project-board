package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.dto.MemberCreateRequest;
import com.example.board.dto.MemberDetailResponse;
import com.example.board.dto.MemberResponse;
import com.example.board.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 : POST /members
    @PostMapping("/members")
    public MemberResponse register(@RequestBody MemberCreateRequest request) {
        Member member = memberService.register(
                request.getEmail(),
                request.getNickname()
        );
        return new MemberResponse(member);
    }

    // 회원조회 : GET /members/{id}
    @GetMapping("/members/{id}")
    public MemberDetailResponse findMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);
        return new MemberDetailResponse(member);
    }

    // 오류처리
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        return e.getMessage();
    }
}
