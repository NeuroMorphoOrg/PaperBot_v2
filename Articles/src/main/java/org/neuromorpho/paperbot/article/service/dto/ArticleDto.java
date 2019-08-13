/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    
    private String id;
    private ArticleDataDto data;
    private HashMap<String, Set<String>> searchPortal;
    private String status;
    private Map<String, Object> metadata;
    private ReconstructionsDto reconstructions;
    private List<ArticleDto> sharedList;
    private Boolean locked;


    public ArticleDto() {
    }

    public ArticleDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReconstructionsDto getReconstructions() {
        return reconstructions;
    }

    public void setReconstructions(ReconstructionsDto reconstructions) {
        this.reconstructions = reconstructions;
    }

   
    public HashMap<String, Set<String>> getSearchPortal() {
        return searchPortal;
    }

    public void setSearchPortal(HashMap<String, Set<String>> searchPortal) {
        this.searchPortal = searchPortal;
    }

    public ArticleDataDto getData() {
        return data;
    }

    public void setData(ArticleDataDto data) {
        this.data = data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArticleDto> getSharedList() {
        return sharedList;
    }

    public void setSharedList(List<ArticleDto> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", searchPortal=" + searchPortal +
                ", status='" + status + '\'' +
                ", metadata=" + metadata +
                ", reconstructions=" + reconstructions +
                ", sharedList=" + sharedList +
                ", locked=" + locked +
                '}';
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
