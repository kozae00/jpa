package com.example.jpa.global;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

           if(postService.count() > 0) {
               return;
           }

           Post p1 = postService.write("title1", "body1");
           Post p2 = postService.write("title2", "body2");
           Post p3 = postService.write("title3", "body3");
        };
    }
}
