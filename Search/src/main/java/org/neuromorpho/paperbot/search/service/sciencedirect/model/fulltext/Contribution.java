package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contribution {

    @XmlElement(name = "authors", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Authors authors;

    @XmlElement(name = "title", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Title title;

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public Title getTitle() {
        return title;
    }

    public String getMainTitle() {
        try {
            return title.getMainTitle();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public void setTitle(Title title) {
        this.title = title;
    }


    public List<String> getAuthorList() {
        return this.authors.getAuthorListStr();
    }


    @Override
    public String toString() {
        return "Contribution{" +
                "authors=" + authors +
                ", title=" + title +
                '}';
    }
}


