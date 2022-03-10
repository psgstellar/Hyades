package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Optional;

public interface TechStackService {

    Optional<TechStack> findById(Long techStackId);

    List<TechStack> findMemberStacks(Long memberId);

    int saveTechStackList(List<TechStack> techStackList) throws DuplicateKeyException;

    int updateTechStackList(List<TechStack> techStackList);

    int removeTechStackList(List<TechStack> techStackList);
}
