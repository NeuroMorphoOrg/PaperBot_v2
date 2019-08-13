package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Figure {


    @XmlElement(name = "label", namespace="http://www.elsevier.com/xml/common/dtd")
    private String label;

    @XmlElement(name = "caption", namespace="http://www.elsevier.com/xml/common/dtd")
    @XmlJavaTypeAdapter(value = ContentHandler.class)
    private String caption;

    @XmlElement(name = "link", namespace="http://www.elsevier.com/xml/common/dtd")
    private Link link;
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }


    public String getLocator() {
        return link.getLocator();
    }
}


