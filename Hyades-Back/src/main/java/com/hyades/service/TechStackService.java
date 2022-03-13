package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Optional;

public interface TechStackService {

    List<TechStack> findAll();

    Optional<TechStack> findById(Long techStackId);

    Optional<TechStack> findByTechName(String techName);

    int saveTechStackList(List<TechStack> techStackList) throws DuplicateKeyException;

    int updateTechStackList(List<TechStack> techStackList);

    int removeTechStackList(List<TechStack> techStackList);
}
