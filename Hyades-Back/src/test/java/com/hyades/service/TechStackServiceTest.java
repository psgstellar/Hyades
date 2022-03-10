package com.hyades.service;

import com.hyades.domain.entity.TechStack;
import com.hyades.mapper.TechStackMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    TechStackMapper techStackMapper;

    @Test
    public void 회원기술스텍_조회() throws Exception {
        // given
        Long memberId = 1L;

        // when
        List<TechStack> findMemberStacks = techStackMapper.findMemberStacks(memberId);

        // then
        assertThat(findMemberStacks.size()).isEqualTo(4);
    }
    
    @Test
    public void 기술스텍_저장() throws Exception {
        // given
        TechStack techStack1 = TechStack.builder()
                .memberId(1L)
                .techName("react")
                .build();

        TechStack techStack2 = TechStack.builder()
                .memberId(1L)
                .techName("vue.js")
                .build();
        
        List<TechStack> techStackList = new ArrayList<>();
        techStackList.add(techStack1);
        techStackList.add(techStack2);
        
        // when
        int saveNum = techStackMapper.insertTechStackList(techStackList);

        // then
        assertThat(saveNum).isEqualTo(2);
    }

    @Test
    public void 기술스텍_중복_오류() throws Exception {
        // given
        TechStack techStack1 = TechStack.builder()
                .memberId(1L)
                .techName("react")
                .build();

        TechStack techStack2 = TechStack.builder()
                .memberId(1L)
                .techName("react")
                .build();

        List<TechStack> techStackList = new ArrayList<>();
        techStackList.add(techStack1);
        techStackList.add(techStack2);

        // then
        assertThrows(DuplicateKeyException.class, () -> {
            techStackMapper.insertTechStackList(techStackList);
        });
    }

    @Test
    public void 기술스텍_삭제() throws Exception {
        // given
        List<TechStack> techStacks = new ArrayList<>();
        TechStack findTechStack1 = techStackMapper.findById(1L);
        TechStack findTechStack2 = techStackMapper.findById(2L);
        techStacks.add(findTechStack1);
        techStacks.add(findTechStack2);

        // when
        int deleteNum = techStackMapper.deleteTechStackList(techStacks);

        // then
        assertThat(deleteNum).isEqualTo(techStacks.size());
        assertThat(ObjectUtils.isEmpty(techStackMapper.findById(1L))).isEqualTo(true);
    }

    @Test
    public void 기술스텍_수정() throws Exception {
        // given
        List<TechStack> memberTechStacks = techStackMapper.findMemberStacks(1L);
        List<String> memberStacknames = new ArrayList<>();
        for (TechStack memberTechStack : memberTechStacks) {
            memberStacknames.add(memberTechStack.getTechName());
        }

        // when
        for (TechStack memberTechStack : memberTechStacks) {
            memberTechStack.setTechName(memberTechStack.getTechName() + " update");
        }
        techStackMapper.updateTechStackList(memberTechStacks);
        List<TechStack> memberTechStacks2 = techStackMapper.findMemberStacks(1L);

        // then
        for (int i = 0; i<memberTechStacks2.size(); i++){
            assertThat(memberTechStacks2.get(i).getTechName()).isNotEqualTo(memberStacknames.get(i));
            System.out.println("i = " + i + " " + memberTechStacks2.get(i).getTechName() + "" + memberStacknames.get(i));
        }
    }
}