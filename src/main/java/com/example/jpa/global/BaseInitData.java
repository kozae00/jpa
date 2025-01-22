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

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    // 프록시 객체를 획득
    @Autowired
    @Lazy
    private BaseInitData self; // 프록시


    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            self.work1();
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                self.work1();
                self.work2();
            }
        };
    }

    @Transactional
    public void work() {
        // 시작

        Comment c1 = commentService.findById(1L).get();
        // SELECT * FROM comment WHERE id = 1;

        Post post = c1.getPost(); // EAGER -> 이미 모든 post정보를 위에서 join으로 가져옴.
        // LAZY -> post -> 비어 있다.
        System.out.println(post.getId()); // post가 null은 아니고. id 하나만 채워져 있다.

        System.out.println(post.getTitle());


        // 끝
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) {
            return;
        }
        Post p1 = postService.write("title1", "body1");

        Comment c1 = Comment.builder()
                .body("comment1")
                .build();

        commentService.save(c1);
        p1.addComment(c1);
        Comment c2 = Comment.builder()
                .body("comment2")
                .build();
        p1.addComment(c2);
        Comment c3 = Comment.builder()
                .body("comment3")
                .build();
        p1.addComment(c3);
        p1.removeComment(c1);
    }

    @Transactional
    public void work2() {
        Post post = postService.findById(1L).get();
        System.out.println("1번 포스트 가져옴");
//        Comment comment = commentService.findById(1L).get();
        List<Comment> comments = post.getComments();
        System.out.println("1번 포스트의 댓글 가져옴");

        String body = comments.get(0).getBody();
        System.out.println("첫번째 댓글 내용 가져옴");
        // select * from comment where id = 1 X
        // 한번에 모든 댓글 정보 가져옴.
        // select * from comment where post_id = 1; O
        comments.get(1); // 2번째 댓글 가져옴. DB 조회 안함.
        // select * from comment where id = 2 X
    }
}