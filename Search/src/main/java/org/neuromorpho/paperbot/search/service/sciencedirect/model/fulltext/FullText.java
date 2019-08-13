package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;



import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "full-text-retrieval-response")
@XmlAccessorType(XmlAccessType.FIELD)
public class FullText {
    
    @XmlElement(name = "originalText")
    private OriginalText originalText;

    @XmlElement(name = "objects")
    private Objects objects;
    
    public OriginalText getOriginalText() {
        return originalText;
    }

    public void setOriginalText(OriginalText originalText) {
        this.originalText = originalText;
    }
    
    public List<Section> getSectionList(){
        return this.originalText.getSectionList();
    }

    public List<BibReference> getReferenceList(){
        return this.originalText.getReferenceList();
    }
    
    public List<Figure> getFigureList(){
        return this.originalText.getFigureList();
    }

    public String getRawText(){
        return this.originalText.getRawText();
    }

    public String getAcknowledgment(){
        return this.originalText.getAcknowledgment();
    }

    public String getAbstract(){
        return this.originalText.getAbstract();
    }

    public String getTitle(){
        return this.originalText.getTitle();
    }

    public AuthorGroup getAuthorGroup(){
        return this.originalText.getAuthorGroup();
    }

    public Objects getObjects() {
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }

    public List<Object> getObjectList() {
        return this.objects.getObjectList();
    }
}


