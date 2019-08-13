package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {


    @XmlElement(name = "article", namespace="http://www.elsevier.com/xml/ja/dtd")
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Section> getSectionList(){
        return this.article.getSectionList();
    }

    public List<BibReference> getReferenceList(){
        return this.article.getReferenceList();
    }
    public List<Figure> getFigureList(){
        return this.article.getFigureList();
    }

    public String getAcknowledgment(){
        return this.article.getAcknowledgment();
    }

    public String getAbstract(){
        return this.article.getAbstract();
    }

    public AuthorGroup getAuthorGroup(){
        return this.article.getAuthorGroup();
    }

    public String getTitle(){
        return this.article.getTitle();
    }
}


