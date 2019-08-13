/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.neuromorpho.paperbot.article.model.article.Author;

import java.time.LocalDate;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDataDto {

    private String pmid;
    private String pmcid;
    private String doi;
    private String link;
    private String journal;
    private String title;
    private List<Author> authorList;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate publishedDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate ocDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate evaluatedDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate approvedDate;
    private List<String> dataUsage;

    public ArticleDataDto() {
    }
    
    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "ArticleDataDto{" +
                "pmid='" + pmid + '\'' +
                ", pmcid='" + pmcid + '\'' +
                ", doi='" + doi + '\'' +
                ", link='" + link + '\'' +
                ", journal='" + journal + '\'' +
                ", title='" + title + '\'' +
                ", authorList=" + authorList +
                ", publishedDate=" + publishedDate +
                ", ocDate=" + ocDate +
                ", evaluatedDate=" + evaluatedDate +
                ", dataUsage=" + dataUsage +
                '}';
    }
    
    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getOcDate() {
        return ocDate;
    }

    public void setOcDate(LocalDate ocDate) {
        this.ocDate = ocDate;
    }

    public LocalDate getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(LocalDate evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public List<String> getDataUsage() {
        return dataUsage;
    }

    public void setDataUsage(List<String> dataUsage) {
        this.dataUsage = dataUsage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;

    }
    

    public String getPmcid() {
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        this.pmcid = pmcid;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }
}
