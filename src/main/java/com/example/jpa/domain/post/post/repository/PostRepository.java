package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// PostRepository가 제공되는 기능을 사용해야 트랜잭션을 수행 -> DB에 반영
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
    List<Post> findByTitleLike(String title);
    List<Post> findByTitleAndBody(String title, String body);
    List<Post> findByOrderByIdDesc();
    List<Post> findTop2ByTitleOrderByIdDesc(String title);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByTitleLike(String title, Pageable pageable);
    List<Post> findByAuthorUsername(String username);
}
