/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.dto.fulltext;


import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.FullText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ArticleContentDtoAssembler {
    
    AuthorGroupDtoAssembler authorGroupAssembler = new AuthorGroupDtoAssembler();
    SectionDtoAssembler sectionAssembler = new SectionDtoAssembler();
    ReferenceDtoAssembler referenceAssembler = new ReferenceDtoAssembler();
    AttachmentDtoAssembler attachmentAssembler = new AttachmentDtoAssembler();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ArticleContentDto createArticleContentDto(FullText fullText, String pdfUrl, String token) {
        try {
            ArticleContentDto articleContentDto = new ArticleContentDto();
            articleContentDto.setTitle(fullText.getTitle());
            articleContentDto.setAbstractContent(fullText.getAbstract());
            
            AuthorGroupDto authorGroupDto = authorGroupAssembler.createAuthorGroupDto(fullText.getAuthorGroup());
            articleContentDto.setAuthorGroup(authorGroupDto);
            
            List<SectionDto> sectionListDto = sectionAssembler.createSectionListDto(fullText.getSectionList());
            articleContentDto.setSectionList(sectionListDto);

            articleContentDto.setRawText(fullText.getRawText());
            
            List<ReferenceDto> referenceListDto = referenceAssembler.createReferenceListDto(fullText.getReferenceList());
            articleContentDto.setReferenceList(referenceListDto);

            List<AttachmentDto> attachmentListDto = attachmentAssembler.createAttachmentListDto(fullText.getObjectList(),
                    fullText.getFigureList(), token);
            articleContentDto.setAttachmentList(attachmentListDto);
            articleContentDto.setPdfUrl(pdfUrl);
            articleContentDto.setAcknowledgment(fullText.getAcknowledgment());
            
            return articleContentDto;
        } catch (NullPointerException ex) {
            log.error("Error creating article content: ", ex);
            return null;
        }
    }

}
