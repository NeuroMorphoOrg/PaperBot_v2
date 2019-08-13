package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Editors {
    
    @XmlElement(name = "editor", namespace="http://www.elsevier.com/xml/common/struct-bib/dtd")
    private List<Author> editorList;

    public List<Author> getEditorList() {
        return editorList;
    }

    public void setEditorList(List<Author> editorList) {
        this.editorList = editorList;
    }

    public List<String> getEditorListStr() {
        return this.editorList.stream().map(x -> x.getGivenName() + " " + x.getSurname()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Editors{" +
                "editorList=" + editorList +
                '}';
    }
}


