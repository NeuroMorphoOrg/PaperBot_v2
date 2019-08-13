package org.neuromorpho.paperbot.search.model;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Portal {

    private ObjectId id;

    private String name;
    private String url;
    private String base;
    private LocalDate startSearchDate;
    private Boolean active;
    private Boolean executed;
    private String db;
    private String searchUrlApi;
    private String contentUrlApi;
    private String token;
    private Log log;

    public Portal(String name) {
        this.name = name;
    }

    public Portal() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getStartSearchDate() {
        if (this.startSearchDate == null) {
            startSearchDate = LocalDate.now();
        }
        return startSearchDate;
    }

    public void setStartSearchDate(LocalDate startSearchDate) {
        this.startSearchDate = startSearchDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
    
    public Boolean hasSearchAPI() {
        return searchUrlApi != null;
    }
    
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSearchUrlApi() {
        return searchUrlApi;
    }

    public void setSearchUrlApi(String searchUrlApi) {
        this.searchUrlApi = searchUrlApi;
    }

    public String getContentUrlApi() {
        return contentUrlApi;
    }

    public void setContentUrlApi(String contentUrlApi) {
        this.contentUrlApi = contentUrlApi;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }
}
