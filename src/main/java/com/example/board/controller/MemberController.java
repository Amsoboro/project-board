package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.dto.request.MemberCreateRequest;
import com.example.board.dto.response.MemberDetailResponse;
import com.example.board.dto.response.MemberResponse;
import com.example.board.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 : POST /members
    @PostMapping("/members")
    public ResponseEntity<MemberResponse> register(@Valid @RequestBody MemberCreateRequest request) {
        Member member = memberService.register(
                request.getEmail(),
                request.getNickname()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberResponse(member));
    }

    // 회원조회 : GET /members/{id}
    @GetMapping("/members/{id}")
    public MemberDetailResponse findMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);
        return new MemberDetailResponse(member);
    }
}
