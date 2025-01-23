package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.tag.entity.Tag;
import com.example.jpa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Getter
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴을 사용
@EntityListeners(AuditingEntityListener.class)
@Setter

public class Post extends BaseTime {

    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) // EAGER, LAZY
    @Builder.Default// mappedBy를 사용하지 않은 쪽이 주인
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    public void addComment(Comment c) {
        comments.add(c);
        c.setPost(this);
    }

    public void addTag(String name) {
//          로직기반 중복체크
//          대신에 equals와 hashcode를 재정의
//        Optional<Tag> oldTag = tags.stream()
//                .filter(tag -> tag.getName().equals(name))
//                .findFirst();
//
//        if(oldTag.isPresent()) {
//            return;
//        }

        Tag tag = Tag.builder()
                .name(name)
                .post(this)
                .build();
        tags.add(tag);

    }
}
