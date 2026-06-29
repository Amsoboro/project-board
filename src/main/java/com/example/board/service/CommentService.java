package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.domain.Member;
import com.example.board.domain.Post;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Comment write(Long postId, Long memberId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다 : " + postId));
        Member author = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. : " + memberId));
        Comment comment = new Comment(content, post, author);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Long id, String content) {
        Comment comment = findComment(id);
        comment.update(content);
        return comment;
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = findComment(id);
        commentRepository.delete(comment);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다. : " + id));
    }

    public List<Comment> findByPost(Long postId) {
        return commentRepository.findByPostIdWithAuthor(postId);
    }
}
