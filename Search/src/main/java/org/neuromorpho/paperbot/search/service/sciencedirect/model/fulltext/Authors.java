package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Authors {
    
    @XmlElement(name = "author", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private List<Author> authorList;

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }


    public List<String> getAuthorListStr() {
        return this.authorList.stream().map(x -> x.getGivenName() + " " + x.getSurname()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Authors{" +
                "authorList=" + authorList +
                '}';
    }
}


