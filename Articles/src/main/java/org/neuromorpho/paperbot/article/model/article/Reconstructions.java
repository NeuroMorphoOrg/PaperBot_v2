/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.model.article;


import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Reconstructions {

    private List<ReconstructionsStatus> currentStatusList;
    private List<ReconstructionsStatus> pastStatusList;

    public Reconstructions() {
    }
    public Reconstructions initializeReconstructionsStatus(Double nReconstructions){
        this.currentStatusList = new ArrayList<>();
        ReconstructionsStatus reconstructionsStatus = new ReconstructionsStatus(nReconstructions);
        this.currentStatusList.add(reconstructionsStatus);
        return this;
    }
    
    public Reconstructions(List<ReconstructionsStatus> currentStatusList) {
        this.currentStatusList = currentStatusList;
    }

    public List<ReconstructionsStatus> getCurrentStatusList() {
        return currentStatusList;
    }

    public List<ReconstructionsStatus> getPastStatusList() {
        return pastStatusList;
    }

    public void setPastStatusList(List<ReconstructionsStatus> pastStatusList) {
        this.pastStatusList = pastStatusList;
    }
    
    public void setCurrentStatusList(List<ReconstructionsStatus> currentStatusList) {
        this.currentStatusList = currentStatusList;
    }

    public void updateCurrentStatusList(List<ReconstructionsStatus> currentStatusList){
        if (currentStatusList != null && this.currentStatusList != null) {
            if (this.pastStatusList == null) {
                this.pastStatusList = new ArrayList();
            }
            //compare all the elements ands save the ones that had changed
            for (ReconstructionsStatus statusOld : this.currentStatusList) {
                this.pastStatusList.add(statusOld);
            }
        }
        this.currentStatusList = currentStatusList;
    }
    
    
    @BsonIgnore
    public String getGlobalStatus(){
        String globalStatus = "Not available";
        for (ReconstructionsStatus status: this.getCurrentStatusList()){
            if (status.getSpecificDetails().getStatus().equals("Available")){
                return "Available";
            } else  if (status.getSpecificDetails().getStatus().equals("Determining availability")){
                globalStatus = "Determining availability";
            }
        }
        return globalStatus;
        
    }

    @Override
    public String toString() {
        return "Reconstructions{" +
                ", currentStatusList=" + currentStatusList +
                ", pastStatusList=" + pastStatusList +
                '}';
    }
}
