package org.neuromorpho.paperbot.article.repository;

import org.bson.Document;
import org.neuromorpho.paperbot.article.model.article.ReconstructionsStatus;

public class ArticleSummary {

    private String specificDetails;
    private String status;
    private Double nArticles;
    private Double nSharedArticles;
    private Double nReconstructions;

    public ArticleSummary(Document document) {
        ReconstructionsStatus.SpecificDetails details = ReconstructionsStatus.SpecificDetails.valueOf(document.getString("_id"));
        this.specificDetails = details.getDetails();
        this.status = details.getStatus();
        this.nArticles = document.getDouble("nArticles");
        this.nSharedArticles = document.getDouble("nSharedArticles");
        this.nReconstructions = document.getDouble("nReconstructions");
    }

    public String getSpecificDetails() {
        return specificDetails;
    }

    public void setSpecificDetails(String specificDetails) {
        this.specificDetails = specificDetails;
    }

    public Double getnArticles() {
        return nArticles;
    }

    public void setnArticles(Double nArticles) {
        this.nArticles = nArticles;
    }

    public Double getnReconstructions() {
        return nReconstructions;
    }

    public void setnReconstructions(Double nReconstructions) {
        this.nReconstructions = nReconstructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getnSharedArticles() {
        return nSharedArticles;
    }

    public void setnSharedArticles(Double nSharedArticles) {
        this.nSharedArticles = nSharedArticles;
    }
}