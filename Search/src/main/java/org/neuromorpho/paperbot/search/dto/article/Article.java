package org.neuromorpho.paperbot.search.dto.article;

import java.time.LocalDate;
import java.util.List;

public class Article {

    private String pmid;
    private String pmcid;
    private String title;
    private String link;
    private String journal;
    private String doi;
    private LocalDate publishedDate;
    private List<Author> authorList;
    private List<String> dataUsage;

    
    public Article() {
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getDataUsage() {
        return dataUsage;
    }

    public void setDataUsage(List<String> dataUsage) {
        this.dataUsage = dataUsage;
    }
    public String getPmcid() {
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        this.pmcid = pmcid;
    }

    @Override
    public String toString() {
        return "Article{" +
                ", pmid='" + pmid + '\'' +
                ", pmcid='" + pmcid + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", journal='" + journal + '\'' +
                ", doi='" + doi + '\'' +
                ", publishedDate=" + publishedDate +
                ", authorList=" + authorList +
                ", dataUsage=" + dataUsage +
                '}';
    }
}
