package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class BibliographySection {


    @XmlElement(name = "bib-reference", namespace="http://www.elsevier.com/xml/common/dtd")
    private List<BibReference> bibReference;

    public List<BibReference> getBibReference() {
        return bibReference;
    }

    public void setBibReference(List<BibReference> bibReference) {
        this.bibReference = bibReference;
    }
    
}


