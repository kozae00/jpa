package com.example.jpa.domain.post.tag.entity;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴을 사용
@EntityListeners(AuditingEntityListener.class)
@Setter

public class Tag extends BaseEntity {

    @Column(length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
