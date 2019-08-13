package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Doc {


    @XmlElement(name = "serial-item", namespace="http://www.elsevier.com/xml/xocs/dtd")
    private Item item;
    
    @XmlElement(name = "rawtext", namespace="http://www.elsevier.com/xml/xocs/dtd")
    private String rawText;
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Section> getSectionList(){
        return this.item.getSectionList();
    }
    
    public List<BibReference> getReferenceList(){
        return this.item.getReferenceList();
    }
    
    public List<Figure> getFigureList(){
        return this.item.getFigureList();
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getAcknowledgment(){
        return this.item.getAcknowledgment();
    }

    public String getAbstract(){
        return this.item.getAbstract();
    }

    public AuthorGroup getAuthorGroup(){
        return this.item.getAuthorGroup();
    }

    public String getTitle(){
        return this.item.getTitle();
    }

}


