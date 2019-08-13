package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Host {

    @XmlTransient
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @XmlElement(name = "issue", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Issue issue;
    @XmlElement(name = "book", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Book book;
    @XmlElement(name = "pages", namespace = "http://www.elsevier.com/xml/common/struct-bib/dtd")
    private Pages pages;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDate() {
        try {
            if (this.issue != null) {
                return this.issue.getDate();
            } else {
                return this.book.getDate();
            }
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public List<String> getPageList() {
        if (this.pages != null) {
            List<String> pageList = new ArrayList<>();
            pageList.add(this.pages.getFirstPage());
            pageList.add(this.pages.getLastPage());
            return pageList;
        }
        return null;
    }

    public String getVolume() {
        try {
            return this.issue.getVolume();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public String getJournal() {
        try {
            return this.issue.getJournal();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public List<String> getEditorList() {
        try {
            return this.book.getEditorList();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public String getPublisherName() {
        try {
            return this.book.getPublisherName();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public String getPublisherLocation() {
        try {
            return this.book.getPublisherLocation();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public String getTitle() {
        try {
            return this.book.getMainTitle();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Host{" +
                "issue=" + issue +
                ", book=" + book +
                ", pages=" + pages +
                '}';
    }
}


