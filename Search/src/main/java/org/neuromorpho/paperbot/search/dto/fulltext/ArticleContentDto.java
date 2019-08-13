package org.neuromorpho.paperbot.search.dto.fulltext;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleContentDto {

    private String title;
    private String abstractContent;
    private AuthorGroupDto authorGroup;
    private List<SectionDto> sectionList;
    private List<AttachmentDto> attachmentList;
    private List<ReferenceDto> referenceList;
    private String acknowledgment;
    private String pdfUrl;
    private String rawText;
    
    public ArticleContentDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public AuthorGroupDto getAuthorGroup() {
        return authorGroup;
    }

    public void setAuthorGroup(AuthorGroupDto authorGroup) {
        this.authorGroup = authorGroup;
    }

    public void setSectionList(List<SectionDto> sectionList) {
        this.sectionList = sectionList;
    }

    public List<SectionDto> getSectionList() {
        return sectionList;
    }

    public List<AttachmentDto> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentDto> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<ReferenceDto> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<ReferenceDto> referenceList) {
        this.referenceList = referenceList;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getAcknowledgment() {
        return acknowledgment;
    }

    public void setAcknowledgment(String acknowledgment) {
        this.acknowledgment = acknowledgment;
    }
}
