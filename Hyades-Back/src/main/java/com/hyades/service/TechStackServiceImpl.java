package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import com.hyades.mapper.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechStackServiceImpl implements TechStackService {

    private final TechStackRepository techStackRepository;

    @Override
    public List<TechStack> findAll() {
        return techStackRepository.findAll();
    }

    @Override
    public Optional<TechStack> findById(Long techStackId) {
        return Optional.ofNullable(techStackRepository.findById(techStackId));
    }

    @Override
    public Optional<TechStack> findByTechName(String techName) {
        return Optional.ofNullable(techStackRepository.findByTechName(techName));
    }

    @Override
    public int saveTechStackList(List<TechStack> techStackList) throws DuplicateKeyException {
        try {
            return techStackRepository.insertTechStackList(techStackList);
        } catch (DuplicateKeyException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateTechStackList(List<TechStack> techStackList) {
        return techStackRepository.updateTechStackList(techStackList);
    }

    @Override
    public int removeTechStackList(List<TechStack> techStackList) {
        return techStackRepository.deleteTechStackList(techStackList);
    }
}
