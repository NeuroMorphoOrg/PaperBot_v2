package org.neuromorpho.paperbot.pubmed.service.results.identifiers;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Identifiers {
    
    @JsonProperty("pmcid")
    private String pmcid;
    @JsonProperty("doi")
    private String doi;
    @JsonProperty("pmid")
    private String pmid;

    public String getPmcid() {
        if (this.pmcid != null) {
            return this.pmcid.replace("PMC", "");
        }
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        this.pmcid = pmcid;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }
}
