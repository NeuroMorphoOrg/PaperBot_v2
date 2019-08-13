package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorGroup {
    
    @XmlElement(name = "author", namespace="http://www.elsevier.com/xml/common/dtd")
    private List<Author> authorList;
    
    @XmlElement(name = "affiliation", namespace="http://www.elsevier.com/xml/common/dtd")
    @XmlJavaTypeAdapter(value = ContentHandler.class)
    private List<String> affiliationList;

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<String> getAffiliationList() {
        return affiliationList;
    }

    public void setAffiliationList(List<String> affiliationList) {
        this.affiliationList = affiliationList;
    }
}


