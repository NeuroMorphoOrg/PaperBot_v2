package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorStr {

    @JsonProperty("$")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
