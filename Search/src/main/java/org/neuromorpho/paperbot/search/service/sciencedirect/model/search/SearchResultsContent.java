package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResultsContent {
    
    @JsonProperty("entry")
    private List<Entry> entryList;

    @JsonProperty("link")
    private List<Link> linkList;

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public Integer getEntryListSize(){
        return this.entryList.size();
    }

    public Boolean isEmpty(){
        return this.entryList.get(0).getError()!= null && 
                this.entryList.get(0).getError().equals("Result set was empty.");
    }
}
