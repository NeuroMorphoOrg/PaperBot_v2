package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {
    
    @JsonProperty("prism:doi")
    private String doi;
    @JsonProperty("prism:url")
    private String url;
    @JsonProperty("dc:title")
    private String title;
    @JsonProperty("prism:publicationName")
    private String publicationName;
    @JsonProperty("authors")
    private Authors authors;
    @JsonProperty("prism:coverDate")
    private String coverDate;

    @JsonProperty("error")
    private String error;
    
    

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public String getCoverDate() {
        return coverDate;
    }

    public void setCoverDate(String coverDate) {
        this.coverDate = coverDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "doi='" + doi + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", publicationName='" + publicationName + '\'' +
                ", authors=" + authors +
                ", coverDate='" + coverDate + '\'' +
                '}';
    }
}
