package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    MemberService memberService;

    @Test
    void 회원가입_성공() throws Exception {
        // given
        Member fakeMember = new Member("test@example.com", "테스터");
        given(memberService.register(any(), any())).willReturn(fakeMember);

        Map<String, String> requestBody = Map.of(
                "email", "test@example.com",
                "nickname", "테스터"
        );

        // when & then
        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated());

    }

    @Test
    void 회원가입_실패_이메일누락() throws Exception {
        // given
        Map<String, String> requestBody = Map.of(
                "email", "",
                "nickname", "누락"
        );

        // when & then
        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").exists());
    }
}
