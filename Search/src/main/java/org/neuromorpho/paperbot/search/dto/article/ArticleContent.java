package org.neuromorpho.paperbot.search.dto.article;


import org.bson.types.ObjectId;

import java.util.List;

public class ArticleContent {

    private ObjectId id;
    private List<Section> sectionList;
    private List<Figure> figureList;
    private List<String> referenceList;

    public ArticleContent() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public List<Figure> getFigureList() {
        return figureList;
    }

    public void setFigureList(List<Figure> figureList) {
        this.figureList = figureList;
    }

    public List<String> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<String> referenceList) {
        this.referenceList = referenceList;
    }
}
