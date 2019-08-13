package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Bibliography {


    @XmlElement(name = "bibliography-sec", namespace="http://www.elsevier.com/xml/common/dtd")
    private BibliographySection bibliographySection;

    public BibliographySection getBibliographySection() {
        return bibliographySection;
    }

    public void setBibliographySection(BibliographySection bibliographySection) {
        this.bibliographySection = bibliographySection;
    }

    public List<BibReference> getReferenceList(){
        return this.bibliographySection.getBibReference();
    }
}


