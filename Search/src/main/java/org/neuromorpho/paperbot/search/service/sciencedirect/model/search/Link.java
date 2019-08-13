package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    @JsonProperty("@ref")
    private String ref;

    @JsonProperty("@href")
    private String link;


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public Boolean existsNextPage (){
        return this.getRef().equals("next");
    }
}
