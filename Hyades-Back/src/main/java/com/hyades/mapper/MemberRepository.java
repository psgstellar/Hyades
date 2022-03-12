package com.hyades.mapper;

import com.hyades.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberRepository {

    List<Member> findAll();

    Member findById(Long id);

    Member findByEmail(String email);

    int save(Member member);

}
