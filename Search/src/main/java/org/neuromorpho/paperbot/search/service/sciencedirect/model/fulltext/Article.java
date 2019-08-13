package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Article {

    @XmlElement(name = "floats", namespace="http://www.elsevier.com/xml/common/dtd")
    private Floats floats;
    
    @XmlElement(name = "head", namespace="http://www.elsevier.com/xml/ja/dtd")
    private Head head;
    
    @XmlElement(name = "body", namespace="http://www.elsevier.com/xml/ja/dtd")
    private Body body;

    @XmlElement(name = "tail", namespace="http://www.elsevier.com/xml/ja/dtd")
    private Tail tail;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Floats getFloats() {
        return floats;
    }

    public void setFloats(Floats floats) {
        this.floats = floats;
    }

    public List<Section> getSectionList(){
        return this.body == null? null: this.body.getSectionList() ;
    }

    public List<BibReference> getReferenceList(){
        return this.tail.getReferenceList();
    }

    public List<Figure> getFigureList(){
        return this.floats == null ? new ArrayList<>(): this.floats.getFigureList();
    }


    public String getAcknowledgment(){
        return this.body.getAcknowledgmentContent();
    }

    public String getAbstract(){
        return this.head.getAbstract();
    }

    public AuthorGroup getAuthorGroup(){
        return this.head.getAuthorGroup();
    }

    public String getTitle(){
        return this.head.getTitle();
    }
}


