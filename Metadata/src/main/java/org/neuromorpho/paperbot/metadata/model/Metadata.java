package org.neuromorpho.paperbot.metadata.model;

import org.bson.types.ObjectId;


import java.util.List;

public class Metadata {

    private ObjectId id;
    private String type;
    private String name;
    private List<String> synonyms;

    public Metadata() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public String toString() {
        return "Metadata{" + "id=" + id + ", type=" + type 
                + ", name=" + name + ", synonyms=" + synonyms + '}';
    }

  

}
