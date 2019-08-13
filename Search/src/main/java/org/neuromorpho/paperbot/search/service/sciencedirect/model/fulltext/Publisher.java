package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {
    
    @XmlElement(name = "name", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private String name;
    @XmlElement(name = "location", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}


