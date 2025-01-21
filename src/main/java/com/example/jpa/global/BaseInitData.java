package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    // 프록시 객체를 획득
    @Autowired
    @Lazy
    private final BaseInitData self; // 프록시

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

            commentService.write(p1, "comment1");
            commentService.write(p1, "comment2");
            commentService.write(p1, "comment3");

        };
    }

    // getParent
    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                self.work();
            }
        };
    }

    @Transactional
    public void work() {
        Comment c1 = commentService.findById(1L).get();

        Post post = c1.getPost();

        System.out.println(post.getId()); // post가 null은 아니고. id 하나만 채워져 있다.

        System.out.println(post.getTitle());
    }
}
