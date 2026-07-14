package com.example.board.service;


import com.example.board.domain.Member;
import com.example.board.domain.Post;
import com.example.board.exception.PostNotFoundException;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Post write(Long memberId, String title, String content) {
        Member author = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다 : " + memberId));
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, String title, String content) {
        Post post = findPost(id);
        post.update(title, content);
        return post;
    }

    @Transactional
    public void delete(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
