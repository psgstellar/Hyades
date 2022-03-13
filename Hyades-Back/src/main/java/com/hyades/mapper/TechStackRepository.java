package com.hyades.mapper;

import com.hyades.domain.entity.TechStack;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TechStackRepository {

    List<TechStack> findAll();

    TechStack findById(Long techStackId);

    TechStack findByTechName(String techName);

    int insertTechStackList(List<TechStack> techStackList);

    int updateTechStackList(List<TechStack> techStackList);

    int deleteTechStackList(List<TechStack> techStackList);
}
