package com.hyades.service;

import com.hyades.domain.entity.Member;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAll();

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    int saveMember(Member member) throws DuplicateKeyException;

}
