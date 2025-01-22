package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;;
    public Post write(String title, String body) {
        // Post 조립
        Post post = Post.builder()
                .title(title)
                .body(body)
                .build();


        // repository 넘김
        return postRepository.save(post);
    }


    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Transactional // Transactional은 'springframework'의 Transactional을 사용해야한다.
    public Post modify(Post post, String title, String body) {
        post.setTitle(title);
        post.setBody(body);
        return post;
    }

    @Transactional // 메서드의 시작이 트랜잭션 시작. 메서드의 끝이 트랜잭션 종료.
    public void modify2(long id, String title, String body) {
        Post post = postRepository.findById(id).get();

        post.setTitle(title);
        post.setBody(body);

    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleLike(String title1) {
        return postRepository.findByTitleLike(title1);
    }

    public List<Post> findByTitleAndBody(String title1, String body1) {
        return postRepository.findByTitleAndBody(title1, body1);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }

    public List<Post> findTop2ByTitleOrderByIdDesc(String title1) {
        return postRepository.findTop2ByTitleOrderByIdDesc(title1);
    }
}
