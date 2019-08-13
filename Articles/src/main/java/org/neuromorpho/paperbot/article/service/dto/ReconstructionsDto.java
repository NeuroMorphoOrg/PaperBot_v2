package org.neuromorpho.paperbot.article.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReconstructionsDto {

    private List<ReconstructionsStatusDto> reconstructionsList;

    public List<ReconstructionsStatusDto> getReconstructionsList() {
        return reconstructionsList;
    }

    public void setReconstructionsList(List<ReconstructionsStatusDto> reconstructionsList) {
        this.reconstructionsList = reconstructionsList;
    }

    private String globalStatus;
    private Double totalReconstructions;

    public ReconstructionsDto() {
        
    }
    

    public String getGlobalStatus() {
        return globalStatus;
    }

    public void setGlobalStatus(String globalStatus) {
        this.globalStatus = globalStatus;
    }

    public Double getTotalReconstructions() {
        return totalReconstructions;
    }

    public void setTotalReconstructions(Double totalReconstructions) {
        this.totalReconstructions = totalReconstructions;
    }

    @Override
    public String toString() {
        return "ReconstructionsDto{" +
                "reconstructionsList=" + reconstructionsList +
                ", globalStatus='" + globalStatus + '\'' +
                ", totalReconstructions=" + totalReconstructions +
                '}';
    }
}
