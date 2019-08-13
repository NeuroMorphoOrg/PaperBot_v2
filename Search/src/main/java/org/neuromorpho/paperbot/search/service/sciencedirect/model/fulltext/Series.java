package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "series")
@XmlAccessorType(XmlAccessType.FIELD)
public class Series {
    
    @XmlElement(name = "title", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Title title;
    @XmlElement(name = "volume-nr", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private String volume;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getJournal() {
        return this.title.getMainTitle();
    }

    @Override
    public String toString() {
        return "Series{" +
                "title=" + title +
                ", volume='" + volume + '\'' +
                '}';
    }
}


