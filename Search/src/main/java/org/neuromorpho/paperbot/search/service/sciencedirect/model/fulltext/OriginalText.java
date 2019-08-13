package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalText {

    @XmlElement(name = "doc", namespace="http://www.elsevier.com/xml/xocs/dtd")
    private Doc doc;

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public List<Section> getSectionList(){
        return this.doc.getSectionList();
    }

    public List<BibReference> getReferenceList(){
        return this.doc.getReferenceList();
    }

    public List<Figure> getFigureList(){
        return this.doc.getFigureList();
    }
    
    public String getRawText(){
        return this.doc.getRawText();
    }

    public String getAcknowledgment(){
        return this.doc.getAcknowledgment();
    }

    public String getAbstract(){
        return this.doc.getAbstract();
    }

    public AuthorGroup getAuthorGroup(){
        return this.doc.getAuthorGroup();
    }

    public String getTitle(){
        return this.doc.getTitle();
    }
}


