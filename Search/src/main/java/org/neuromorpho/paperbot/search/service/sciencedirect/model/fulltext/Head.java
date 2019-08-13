package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Head {

    @XmlElement(name = "title", namespace="http://www.elsevier.com/xml/common/dtd")
    private String title;
    @XmlElement(name = "author-group", namespace="http://www.elsevier.com/xml/common/dtd")
    private AuthorGroup authorGroup;
    @XmlElement(name = "abstract", namespace="http://www.elsevier.com/xml/common/dtd")
    private List<Abstract> abstractList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorGroup(AuthorGroup authorGroup) {
        this.authorGroup = authorGroup;
    }

    public AuthorGroup getAuthorGroup() {
        return authorGroup;
    }

    public List<Abstract> getAbstractList() {
        return abstractList;
    }

    public void setAbstractList(List<Abstract> abstractList) {
        this.abstractList = abstractList;
    }

    public String getAbstract() {
        List<Abstract> abstractList = this.abstractList.stream()
                .filter(a -> a.isAbstract())
                .collect(Collectors.toList());
        return abstractList.size()>0 ? abstractList.get(0).getContent() : null;
    }
}


