package com.hyades.service;

import com.hyades.domain.entity.Member;
import com.hyades.mapper.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberRepository.findById(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(memberRepository.findByEmail(email));
    }

    @Override
    public int saveMember(Member member) {
        try {
            memberRepository.save(member);
        } catch (DuplicateKeyException e){
            e.printStackTrace();
        }
        return 0;
    }

}
