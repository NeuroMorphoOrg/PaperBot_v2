package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reference {
    
    @XmlElement(name = "contribution", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Contribution contribution;
    @XmlElement(name = "host", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Host host;

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<String> getAuthorList() {
        return this.contribution.getAuthorList();
    }
    public LocalDate getDate() {
        return this.host.getDate();
    }
    public String getJournal() {
        return this.host.getJournal();
    }
    public List<String> getPageList() {
        return this.host.getPageList();
    }
    public String getTitle() {
        if (this.contribution.getMainTitle() != null){ //is an issue
            return this.contribution.getMainTitle();
        } else { //is a book
            return this.host.getTitle();
        }
    }
    
    
    public String getVolume() {
        return this.host.getVolume();
    }

    public List<String> getEditorList() {
        return this.host.getEditorList();
    }

    public String getPublisherName() {
        return this.host.getPublisherName();
    }
    public String getPublisherLocation() {
        return this.host.getPublisherLocation();
    }


    @Override
    public String toString() {
        return "Reference{" +
                "contribution=" + contribution +
                ", host=" + host +
                '}';
    }
}


