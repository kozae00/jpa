package com.example.jpa.domain.post.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("글 2개 작성")
    @Transactional // @Transactional을 사용하면 @Rollback 생략 가능
    @Rollback // DB에 반영하고 싶으면, @Rollback(false)로 설정
    public void t1() {
        postService.write("title1", "body1");
        postService.write("title2", "body2");
    }
}
