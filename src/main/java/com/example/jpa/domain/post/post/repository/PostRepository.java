package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// PostRepository가 제공되는 기능을 사용해야 트랜잭션을 수행 -> DB에 반영
public interface PostRepository extends JpaRepository<Post, Long> { }
