package com.example.jpa.domain.post.tag.entity;

import com.example.jpa.domain.post.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴을 사용
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag {

    @EmbeddedId
    private TagId id;

//    @Column(length = 100)
//    @EqualsAndHashCode.Include
//    @MapsId("name")
//    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Include
    @MapsId("postId")
    private Post post;
}
