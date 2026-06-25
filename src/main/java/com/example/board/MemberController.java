package com.example.board;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 : POST /members?email=... &nickname=...
    @PostMapping("/members")
    public Member register(@RequestParam String email,
                           @RequestParam String nickname) {
        return memberService.register(email, nickname);
    }

    // 회원조회 : GET /members/{id}
    @GetMapping("/members/{id}")
    public Member findMember(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    // 오류처리
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        return e.getMessage();
    }
}
