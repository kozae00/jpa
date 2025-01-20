package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
