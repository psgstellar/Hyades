package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import com.hyades.mapper.TechStackRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TechStackServiceTest {

    @Autowired
    TechStackRepository techStackRepository;

    @Test
    public void 전체_기술스텍_조회() throws Exception {

        // when
        List<TechStack> allTechStacks = techStackRepository.findAll();

        // then
        assertThat(allTechStacks.size()).isEqualTo(13);
    }

    @Test
    public void 기술스텍_저장() throws Exception {
        // given
        TechStack techStack1 = TechStack.builder()
                .techName("node.js")
                .build();

        TechStack techStack2 = TechStack.builder()
                .techName("typescript")
                .build();

        List<TechStack> techStackList = new ArrayList<>();
        techStackList.add(techStack1);
        techStackList.add(techStack2);

        // when
        int saveNum = techStackRepository.insertTechStackList(techStackList);

        // then
        for (TechStack techStack : techStackList) {
            System.out.println("techStack.getTechStackId() = " + techStack.getTechStackId());
        }
        assertThat(saveNum).isEqualTo(2);
    }

    @Test
    public void 기술스텍_중복_오류() throws Exception {
        // given
        TechStack techStack1 = TechStack.builder()
                .techName("node.js")
                .build();

        TechStack techStack2 = TechStack.builder()
                .techName("node.js")
                .build();

        List<TechStack> techStackList = new ArrayList<>();
        techStackList.add(techStack1);
        techStackList.add(techStack2);

        // then
        assertThrows(DuplicateKeyException.class, () -> {
            techStackRepository.insertTechStackList(techStackList);
        });
    }

    @Test
    public void 기술스텍_삭제() throws Exception {
        // given
        List<TechStack> techStacks = new ArrayList<>();
        TechStack findTechStack1 = techStackRepository.findById(3L); // "c"
        TechStack findTechStack2 = techStackRepository.findById(4L); // "c++"
        techStacks.add(findTechStack1);
        techStacks.add(findTechStack2);

        // when
        int deleteNum = techStackRepository.deleteTechStackList(techStacks);

        // then
        assertThat(deleteNum).isEqualTo(techStacks.size());
        assertThat(ObjectUtils.isEmpty(techStackRepository.findById(3L))).isEqualTo(true);
        assertThat(ObjectUtils.isEmpty(techStackRepository.findById(4L))).isEqualTo(true);
    }

    @Test
    public void 사용중인_기술스텍_삭제_오류() throws Exception {
        // given
        List<TechStack> techStacks = new ArrayList<>();
        TechStack findTechStack1 = techStackRepository.findById(1L); // "java"
        techStacks.add(findTechStack1);

        // when
        // then
        assertThrows(DataIntegrityViolationException.class, () -> {
            techStackRepository.deleteTechStackList(techStacks);
        });
    }

    @Test
    public void 기술스텍_수정() throws Exception {
        // given
        List<TechStack> findTechStacks = new ArrayList<>();
        findTechStacks.add(techStackRepository.findByTechName("java"));
        findTechStacks.add(techStackRepository.findByTechName("python"));

        // when
        List<TechStack> updateTechStacks = new ArrayList<>();
        for (TechStack findTechStack : findTechStacks) {
            updateTechStacks.add(TechStack.builder()
                    .techStackId(findTechStack.getTechStackId())
                    .techName(findTechStack.getTechName() + "_update") // java_update, python_update
                    .build());
        }
        techStackRepository.updateTechStackList(updateTechStacks);

        // then
        assertThat(techStackRepository.findById(findTechStacks.get(0).getTechStackId()).getTechName())
                .isEqualTo("java_update"); // findTechStacks(0) <- before update : techStackName = java
        assertThat(techStackRepository.findById(findTechStacks.get(1).getTechStackId()).getTechName())
                .isEqualTo("python_update"); // findTechStacks(1) <- before update : techStackName = python
    }
}