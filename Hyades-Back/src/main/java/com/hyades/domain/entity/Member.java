package com.hyades.domain.entity;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long memberId;
    private String email;
    private String nickname;
    private String profileImg;

}
