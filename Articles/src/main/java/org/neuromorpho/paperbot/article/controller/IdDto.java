package org.neuromorpho.paperbot.article.controller;


import org.bson.types.ObjectId;

class IdDto {

    private String id;

    public IdDto(ObjectId id) {
        this.id = id.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
