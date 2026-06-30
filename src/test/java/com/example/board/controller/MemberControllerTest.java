package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;


}
