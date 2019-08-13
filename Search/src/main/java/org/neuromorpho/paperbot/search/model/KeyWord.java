package org.neuromorpho.paperbot.search.model;

import org.bson.types.ObjectId;

public class KeyWord {

    private ObjectId id;
    private String name;
    private String collection;
    private Boolean executed;

    public KeyWord() {
    }

    public KeyWord(String name, String collection) {
        this.name = name;
        this.collection = collection;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }
}
