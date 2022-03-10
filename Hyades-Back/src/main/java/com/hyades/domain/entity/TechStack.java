package com.hyades.domain.entity;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechStack {

    private Long id;
    private Long memberId;
    private String techName;

}
