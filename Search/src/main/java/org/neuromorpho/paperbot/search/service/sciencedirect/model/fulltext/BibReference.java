package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class BibReference {


    @XmlElement(name = "reference", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Reference referece;

    public Reference getReferece() {
        return referece;
    }

    public void setReferece(Reference referece) {
        this.referece = referece;
    }

    public List<String> getAuthorList() {
        return this.referece.getAuthorList();
    }
    
    public LocalDate getDate() {
        return this.referece.getDate();
    }
    public String getJournal() {
        return this.referece.getJournal();
    }
    
    public List<String> getPageList() {
        return this.referece.getPageList();
    }
    public String getTitle() {
        return this.referece.getTitle();
    }

    public String getVolume() {
        return this.referece.getVolume();
    }

    public List<String> getEditorList() {
        return this.referece.getEditorList();
    }

    public String getPublisherName() {
        return this.referece.getPublisherName();
    }
    public String getPublisherLocation() {
        return this.referece.getPublisherLocation();
    }

    @Override
    public String toString() {
        return "BibReference{" +
                "referece=" + referece +
                '}';
    }
}


