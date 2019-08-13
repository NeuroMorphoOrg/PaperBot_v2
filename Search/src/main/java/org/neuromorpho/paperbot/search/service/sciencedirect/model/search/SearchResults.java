package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResults {
    
    @JsonProperty("search-results")
    private SearchResultsContent results;
    
    public SearchResultsContent getResults() {
        return results;
    }

    public void setResults(SearchResultsContent results) {
        this.results = results;
    }
    
    
    public Integer getResultsSize(){
        return this.results.getEntryListSize();
    }
    
    public List<Entry> getEntryList(){
        return this.results.getEntryList();
    }
    public List<Link> getLinkList(){
        return this.results.getLinkList();
    }
    
    public Boolean isEmpty(){
        return this.results.isEmpty();
    }

}
