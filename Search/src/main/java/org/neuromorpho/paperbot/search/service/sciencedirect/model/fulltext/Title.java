package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "title")
@XmlAccessorType(XmlAccessType.FIELD)
public class Title {
    
    @XmlElement(name = "maintitle", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    @XmlJavaTypeAdapter(value = ContentHandler.class)
    private String mainTitle;

    
    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    @Override
    public String toString() {
        return "Title{" +
                "mainTitle='" + mainTitle + '\'' +
                '}';
    }
}


