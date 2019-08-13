package org.neuromorpho.paperbot.article.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SharedDto {

    public SharedDto() {
    }
    
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "SharedDto{" +
                "id='" + id + '\'' +
                '}';
    }
}

  
