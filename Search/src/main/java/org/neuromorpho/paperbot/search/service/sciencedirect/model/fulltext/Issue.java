package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Issue {
    
    @XmlElement(name = "series", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Series series;
    @XmlElement(name = "date", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class) 
    private LocalDate date;

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVolume() {
        return this.series.getVolume();
    }

    public String getJournal() {
        return this.series.getJournal();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "series=" + series +
                ", date=" + date +
                '}';
    }
}


