package org.neuromorpho.paperbot.article.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReconstructionsStatusDto {
    
    private Integer id;
    private String statusDetails;
    private Date expirationDate;
    private Double nReconstructions;
    private Date date;
    

    public ReconstructionsStatusDto() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getnReconstructions() {
        return nReconstructions;
    }

    public void setnReconstructions(Double nReconstructions) {
        this.nReconstructions = nReconstructions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReconstructionsStatusDto{" +
                "id=" + id +
                ", statusDetails='" + statusDetails + '\'' +
                ", expirationDate=" + expirationDate +
                ", nReconstructions=" + nReconstructions +
                '}';
    }
}
