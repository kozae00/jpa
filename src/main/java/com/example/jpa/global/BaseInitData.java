package com.example.jpa.global;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1)
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

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return args -> {
            Post post = postService.findById(1L).get();

            Thread.sleep(1000);

            // postService.modify(post, "new title", "new body");
            postService.modify2(1L, "new title", "new body");
        };
    }
}
