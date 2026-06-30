package com.example.board.service;

import com.example.board.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입_성공() {
        // given
        String email = "test@example.com";
        String nickname = "테스터";

        // when
        Member member = memberService.register(email, nickname);

        // then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getEmail()).isEqualTo(email);
    }

    @Test
    void 중복_이메일_가입_실패() {
        // given
        memberService.register("dup@example.com", "첫번째");

        // when & then
        assertThatThrownBy(() -> memberService.register("dup@example.com", "두번째"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 사용 중인 이메일");
    }
}
