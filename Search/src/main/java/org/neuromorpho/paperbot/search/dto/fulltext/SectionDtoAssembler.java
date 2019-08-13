/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.dto.fulltext;



import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.Section;

import java.util.ArrayList;
import java.util.List;


public class SectionDtoAssembler {
    
    protected List<SectionDto> createSectionListDto(List<Section> sectionList) {
        List<SectionDto> sectionDtoList = new ArrayList();
        if (sectionList != null) {
            for (Section section : sectionList) {
                SectionDto sectionDto = this.createSectionDto(section);
                sectionDtoList.add(sectionDto);
            }
        }
        return sectionDtoList;

    }

    private SectionDto createSectionDto(Section section) {
        SectionDto sectionDto = new SectionDto();
        sectionDto.setTitle(section.getSectionTitle());
        sectionDto.setParagraphList(section.getParagraphList());
        sectionDto.setSectionList(this.createSectionListDto(section.getSectionList()));
        return sectionDto;
    }
    

}
