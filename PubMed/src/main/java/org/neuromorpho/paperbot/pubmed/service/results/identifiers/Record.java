package org.neuromorpho.paperbot.pubmed.service.results.identifiers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Record {
    
    @JsonProperty("records")
    private List<Identifiers> identifiersList;

    public List<Identifiers> getIdentifiersList() {
        return identifiersList;
    }

    public void setIdentifiersList(List<Identifiers> identifiersList) {
        this.identifiersList = identifiersList;
    }
}
