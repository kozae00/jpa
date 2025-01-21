package com.example.jpa.domain.post.comment.entity;

import com.example.jpa.domain.post.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Setter

public class Comment {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Setter(AccessLevel.PRIVATE)
    private Long id; // long -> null X, Long -> null O

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;

//    @ManyToOne // Comment가 1 : M 에서 M을 맡기 때문에, 외래키가 생성된다. 외래키는 M쪽으로 생성.
//    @JoinColumn(name = "fk_post") // 외래키 이름을 지정
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // Post 안에는 여러 정보가 많음. @ManyToOne을 사용해 DB에 어떻게 저장할지 알려줌

    @Column(columnDefinition = "TEXT")
    private String body;
}
