package com.example.jpa.domain.post.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// @Setter // Setter는 사용하지 않는 것이 좋다. Setter를 사용하면 객체의 불변성이 깨지기 때문
@Entity
@Getter
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴을 사용
// AllArgsConstructor, NoArgsConstructor, Builder를 사용하면 생성자를 직접 만들지 않아도 된다. 3개는 항상 같이 묶여야 함.

public class Post {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id; // long -> null X, Long  -> null O : JPA 입장에서는 null이 가능해야 하므로 원시 -> 객체타입으로 변경
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;

}
