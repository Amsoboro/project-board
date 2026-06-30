package com.example.board.dto.response;

import com.example.board.domain.Post;

public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorNickname; // 닉네임만 노출하게(이메일 X)

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.authorNickname = post.getAuthor().getNickname();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }
}
