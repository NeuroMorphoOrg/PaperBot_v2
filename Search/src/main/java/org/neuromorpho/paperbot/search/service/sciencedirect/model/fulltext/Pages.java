package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pages")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pages {
    
    @XmlElement(name = "first-page", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private String firstPage;
    @XmlElement(name = "last-page", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private String lastPage;

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }
}


