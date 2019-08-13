/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.pubmed.model;

import java.time.LocalDate;
import java.util.List;

public class Article {


    private String pmid;
    private String pmcid;
    private String doi;
    private String journal;
    private String title;
    private LocalDate ocDate;
    private LocalDate publishedDate;
    private List<Author> authorList;


    public Article() {
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getOcDate() {
        return ocDate;
    }

    public void setOcDate(LocalDate ocDate) {
        this.ocDate = ocDate;
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

    public String getPmcid() {
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        this.pmcid = pmcid;
    }

    public enum DB {
        PUBMED("pubmed"),
        PUBMEDCENTRAL("pmc");

        private final String db;

        private DB(String u) {
            db = u;
        }

        public static DB getDB(String value) {
            for (DB v : values()) {
                if (v.getDB().equalsIgnoreCase(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException();
        }

        public String getDB() {
            return db;
        }

    }

}
