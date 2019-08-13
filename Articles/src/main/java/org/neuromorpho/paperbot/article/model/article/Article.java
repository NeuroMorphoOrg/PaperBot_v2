/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.model.article;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.neuromorpho.paperbot.article.exceptions.ExceptionAssigningReconstructions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

public class Article {

    @BsonIgnore
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ObjectId id;

    private ArticleData data;

    private ArticleStatus status;

    private HashMap<String, Set<String>> searchPortal;

    private Map<String, Object> metadata;

    private Reconstructions reconstructions;

    private List<Shared> sharedList;

    private String pdf;
    
    private Float distance;
    
    private Double score;
    
    private Boolean locked;


    public Article() {
    }
    
    public Article(ArticleData data) {
        this.data = data;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArticleData getData() {
        return data;
    }

    public void setData(ArticleData data) {
        this.data = data;
    }

    public Reconstructions getReconstructions() {
        return reconstructions;
    }

    public void setReconstructions(Reconstructions reconstructions) {
        this.reconstructions = reconstructions;
    }


    public HashMap<String, Set<String>> getSearchPortal() {
        return searchPortal;
    }

    public void setSearchPortal(HashMap<String, Set<String>> searchPortal) {
        this.searchPortal = searchPortal;
    }


    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public Object getMetadataValue(String key) {
        return this.metadata.get(key);
    }


    public List<Shared> getSharedList() {
        return sharedList;
    }

    public void setSharedList(List<Shared> sharedList) {
        this.sharedList = sharedList;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @BsonIgnore
    public Boolean isEmptyEvaluatedDate() {
        return this.data.isEmptyEvaluatedDate();
    }
    
    
    @BsonIgnore
    public void updateCollection(ArticleStatus status) {
        try {
            if (this.data.isEmptyEvaluatedDate()) {
                this.data.setEvaluatedDate(LocalDate.now());
            }
            if (status.isPositive()) {
                this.data.setApprovedDate(LocalDate.now());
                if (this.data.getDataUsage().contains(ArticleData.DataUsage.DESCRIBING_NEURONS) &&
                        this.getMetadataValue("nReconstructions") != null &&
                        (Integer) this.getMetadataValue("nReconstructions") != 0 &&
                        this.reconstructions == null) {
                    Reconstructions reconstructions = new Reconstructions();
                    this.setReconstructions(reconstructions.initializeReconstructionsStatus(
                            Double.valueOf((Integer) this.getMetadataValue("nReconstructions"))));
                }
            }
        } catch (Exception e) {
            throw new ExceptionAssigningReconstructions("Error assigning new reconstructions");
        }
    }

    @BsonIgnore
    public Boolean hasDoi() {
        return this.data.hasDoi();
    }

    @BsonIgnore
    public Boolean hasPmid() {
        return this.data.hasPmid();
    }

    @BsonIgnore
    public Boolean hasPmcid() {
        return this.data.hasPmcid();
    }

    @BsonIgnore
    public Boolean hasPublishedDate() {
        return this.data.hasPublishedDate();
    }

    @BsonIgnore
    public Boolean hasJournal() {
        return this.data.hasJournal();
    }

    @BsonIgnore
    public Boolean hasTitle() {
        return !this.data.hasTitle();
    }

    @BsonIgnore
    public Boolean hasAuthorList() {
        return this.data.hasAuthorList();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @BsonIgnore
    public void setOcDate() {
        this.data.setOcDate(LocalDate.now());
    }

    public Float getDistance() {
        return distance;
    }
    @BsonIgnore
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void updateSearchPortal(String portalName, String keyWord) {
        if (this.searchPortal == null) {
            this.searchPortal = new HashMap();
        }
        this.searchPortal.computeIfAbsent(portalName, k -> new HashSet<>()).add(keyWord);

    }

    public void updateCurrentStatusList(List<ReconstructionsStatus> currentStatusList) {
        if (this.reconstructions == null) {
            this.reconstructions = new Reconstructions();
        }
        this.reconstructions.updateCurrentStatusList(currentStatusList);
    }


    public boolean similarData(Article article){
        return this.data.similarData(article.data, article.getScore());
    }
    
    public enum ArticleStatus {
        ALL("all", "All"),
        TO_EVALUATE("article", "Pending evaluation"),
        POSITIVE("article.positives", "Positive"),
        NEGATIVE("article.negatives", "Negative"),
        EVALUATED("article.evaluated", "Evaluated"),
        INACCESSIBLE("article.inaccessible", "Inaccessible"),
        NEUROMORPHO("article.neuromorpho", "Neuromorpho");

        private String collection;
        private String status;

        ArticleStatus(String s, String a) {
            collection = s;
            status = a;
        }

        public static ArticleStatus getArticleStatus(String value) {
            for (ArticleStatus v : values()) {
                if (v.getStatus().equalsIgnoreCase(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }

        public Boolean isPositive() {
            return this.equals(ArticleStatus.POSITIVE);
        }

        public Boolean isNegative() {
            return this.equals(ArticleStatus.NEGATIVE);
        }

        public Boolean isEvaluated() {
            return this.equals(ArticleStatus.EVALUATED);
        }

        public Boolean isToEvaluate() {
            return this.equals(ArticleStatus.TO_EVALUATE);
        }

        public Boolean isInaccessible() {
            return this.equals(ArticleStatus.INACCESSIBLE);
        }

        public String getCollection() {
            return this.collection;
        }

        public String getStatus() {
            return this.status;
        }

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", data=" + data +
                ", status=" + status +
                ", searchPortal=" + searchPortal +
                ", metadata=" + metadata +
                ", reconstructions=" + reconstructions +
                ", sharedList=" + sharedList +
                '}';
    }
}
