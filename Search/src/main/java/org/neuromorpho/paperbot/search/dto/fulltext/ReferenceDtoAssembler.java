/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.dto.fulltext;


import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.BibReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ReferenceDtoAssembler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected List<ReferenceDto> createReferenceListDto(List<BibReference> referenceList) {
        List<ReferenceDto> referenceDtoList = new ArrayList();
        for (BibReference reference : referenceList) {
            ReferenceDto referenceDto = this.createReferenceDto(reference);
            referenceDtoList.add(referenceDto);
        }
        return referenceDtoList;

    }

    private ReferenceDto createReferenceDto(BibReference reference) {
        ReferenceDto referenceDto = new ReferenceDto();
        try {
            referenceDto.setAuthorList(reference.getAuthorList());
            referenceDto.setDate(reference.getDate());
            referenceDto.setJournal(reference.getJournal());
            referenceDto.setPages(reference.getPageList());
            referenceDto.setTitle(reference.getTitle());
            referenceDto.setVolume(reference.getVolume());
            referenceDto.setPublisherLocation(reference.getPublisherLocation());
            referenceDto.setPublisherName(reference.getPublisherName());
            referenceDto.setEditorList(reference.getEditorList());
        } catch (NullPointerException ex) {
            log.debug(reference.toString());
            log.error("Error: ", ex);
        }
        return referenceDto;

    }


}
