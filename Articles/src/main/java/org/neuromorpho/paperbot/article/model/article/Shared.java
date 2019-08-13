/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.model.article;

import org.bson.types.ObjectId;

public class Shared {

    private ObjectId sharedId;

    public ObjectId getSharedId() {
        return sharedId;
    }

    public void setSharedId(ObjectId sharedId) {
        this.sharedId = sharedId;
    }

    public Shared() {
    }

    public Shared(String sharedId) {
        this.sharedId = new ObjectId(sharedId);
    }

    @Override
    public String toString() {
        return "SharedReconstructions{" +
                "sharedArticleId=" + sharedId +
                '}';
    }
}
