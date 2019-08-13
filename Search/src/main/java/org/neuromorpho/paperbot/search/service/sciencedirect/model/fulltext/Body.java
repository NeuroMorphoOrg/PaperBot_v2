package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Body {


    @XmlElement(name = "sections", namespace="http://www.elsevier.com/xml/common/dtd")
    private Sections sections;
    @XmlElement(name = "acknowledgment", namespace="http://www.elsevier.com/xml/common/dtd")
    private Acknowledgment acknowledgment;

    public Sections getSections() {
        return sections;
    }

    public void setSections(Sections sections) {
        this.sections = sections;
    }

    public List<Section> getSectionList(){
        return this.sections.getSectionList();
    }

    public String getAcknowledgmentContent() {
        return acknowledgment.getContent();
    }

    public Acknowledgment getAcknowledgment() {
        return acknowledgment;
    }

    public void setAcknowledgment(Acknowledgment acknowledgment) {
        this.acknowledgment = acknowledgment;
    }
}


