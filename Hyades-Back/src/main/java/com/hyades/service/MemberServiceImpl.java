package com.hyades.service;

import com.hyades.domain.entity.Member;
import com.hyades.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(memberMapper.findById(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.of(memberMapper.findByEmail(email));
    }

    @Override
    public int saveMember(Member member) {
        try {
            return memberMapper.insertMember(member);
        } catch (DuplicateKeyException e){
            e.printStackTrace();
        }
        return 0;
    }

}
