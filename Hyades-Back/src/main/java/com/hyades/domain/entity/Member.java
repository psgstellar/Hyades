package com.hyades.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long id;
    private String email;
    private String nickname;
    private String profileImg;

}
