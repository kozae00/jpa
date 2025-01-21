package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.post.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴을 사용
@EntityListeners(AuditingEntityListener.class)
@Setter

public class Post {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Setter(AccessLevel.PRIVATE)
    private Long id; // long -> null X, Long  -> null O : JPA 입장에서는 null이 가능해야 하므로 원시 -> 객체타입으로 변경

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;

    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // mppaedBy = "???" 안에는 상대 클래스(post)의 변수명을 넣어준다. mappedBy를 사용하지 않은 쪽이 관계의 주인이다.
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment c1) {
        comments.add(c1);
        c1.setPost(this); // * 외래키 때문에 양방향 관계를 맺어준다.
    }

    public void removeComment(Comment c1) {
        comments.remove(c1);
    }
}
