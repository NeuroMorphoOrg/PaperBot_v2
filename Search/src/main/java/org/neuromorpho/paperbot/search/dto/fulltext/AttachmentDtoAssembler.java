/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.dto.fulltext;


import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.Figure;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class AttachmentDtoAssembler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected List<AttachmentDto> createAttachmentListDto(List<Object> objectList,
                                                          List<Figure> figureList, String token) {
        List<AttachmentDto> attachmentDtoList = new ArrayList();
        for (Figure figure : figureList) {
            for (Object object : objectList) {
                if (object.getRef().equals(figure.getLocator())) {
                    AttachmentDto attachmentDto = this.createAttachmentDto(object, figure, token);
                    attachmentDtoList.add(attachmentDto);
                }
            }
        }
        return attachmentDtoList;

    }

    private AttachmentDto createAttachmentDto(Object object, Figure figure, String token) {
        AttachmentDto attachmentDto = new AttachmentDto();
        try {
            attachmentDto.setMimetype(object.getMimetype());
            attachmentDto.setType(object.getType());
            attachmentDto.setUrl(object.getUrl().substring(0, object.getUrl().indexOf("?")+1) + token);
            attachmentDto.setLabel(figure.getLabel());
            attachmentDto.setCaption(figure.getCaption());
        } catch (NullPointerException ex) {
            log.debug(object.toString());
            log.debug(figure.toString());
            log.error("Error", ex);
        }
        return attachmentDto;
    }


}
