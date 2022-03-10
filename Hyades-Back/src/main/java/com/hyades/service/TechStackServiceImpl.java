package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import com.hyades.mapper.TechStackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechStackServiceImpl implements TechStackService {

    private final TechStackMapper techStackMapper;

    @Override
    public Optional<TechStack> findById(Long techStackId) {
        return Optional.of(techStackMapper.findById(techStackId));
    }

    @Override
    public List<TechStack> findMemberStacks(Long memberId) {
        return techStackMapper.findMemberStacks(memberId);
    }

    @Override
    public int saveTechStackList(List<TechStack> techStackList) throws DuplicateKeyException {
        try {
            return techStackMapper.insertTechStackList(techStackList);
        } catch (DuplicateKeyException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateTechStackList(List<TechStack> techStackList) {
        return techStackMapper.updateTechStackList(techStackList);
    }

    @Override
    public int removeTechStackList(List<TechStack> techStackList) {
        return techStackMapper.deleteTechStackList(techStackList);
    }
}
