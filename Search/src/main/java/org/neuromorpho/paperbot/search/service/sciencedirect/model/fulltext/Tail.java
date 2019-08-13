package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tail {


    @XmlElement(name = "bibliography", namespace="http://www.elsevier.com/xml/common/dtd")
    private Bibliography bibliography;

    public Bibliography getBibliography() {
        return bibliography;
    }

    public void setBibliography(Bibliography bibliography) {
        this.bibliography = bibliography;
    }

    public List<BibReference> getReferenceList(){
        return this.bibliography.getReferenceList();
    }
}


