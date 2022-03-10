package com.hyades.service;

import com.hyades.domain.entity.Member;
import com.hyades.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    public void 전체_회원_검색() throws Exception {

        // when
        List<Member> allMembers = memberMapper.findAll();
        for (Member allMember : allMembers) {
            System.out.println("allMember.getEmail() = " + allMember.getId());
        }

        // then
        assertThat(allMembers.size()).isEqualTo(5);
    }

    @Test
    public void 없는_회원_검색() throws Exception {

        // when
        Member findMember = memberMapper.findByEmail("asd@email.com");

        // then
        assertThat(ObjectUtils.isEmpty(findMember)).isEqualTo(true);

    }

    @Test
    public void 회원_저장() throws Exception {
        // given
        Member member = Member.builder()
                .email("membe1@email.com")
                .nickname("member")
                .profileImg("profile_img_source")
                .build();

        // when
        int saveNum = memberMapper.insertMember(member);
        Member findMember = memberMapper.findByEmail(member.getEmail());

        // then
        assertThat(saveNum).isEqualTo(1);
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(findMember.getProfileImg()).isEqualTo(member.getProfileImg());
    }

    @Test
    public void 이메일_중복_에러() throws Exception {
        // given
        Member member1 = Member.builder()
                .email("testMember1@email.com")
                .nickname("testMember1")
                .profileImg("profile_img_source1")
                .build();

        Member member2 = Member.builder()
                .email("testMember1@email.com")
                .nickname("testMember2")
                .profileImg("profile_img_source2")
                .build();

        // when
        memberMapper.insertMember(member1);

        // then
        //memberMapper.insertMember(member2);
        assertThrows(DuplicateKeyException.class, () -> {
            memberMapper.insertMember(member2);
        });
    }
}