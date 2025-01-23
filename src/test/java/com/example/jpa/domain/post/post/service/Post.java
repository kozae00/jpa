package com.example.jpa.domain.post.post.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // (onlyExplicitlyIncluded = true)를 사용하면 id만 비교하도록 설정 가능 -> 생략 가능
// 1. alrt + insert -> Generate -> equals and hashcode
// 2. 애노테이션 @EqualsAndHashCode를 사용하면 자동 완성 가능
public class Post {

    @EqualsAndHashCode.Include // .Include 어노테이션을 사용하면 id만 비교하도록 설정 가능 -> 생략 가능
    private long id;

    private String title;

}
