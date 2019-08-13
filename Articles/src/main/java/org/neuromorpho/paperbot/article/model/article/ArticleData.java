package org.neuromorpho.paperbot.article.model.article;

import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ArticleData {
    @BsonIgnore
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String pmid;
    private String pmcid;
    private String doi;
    private String link;
    private String journal;
    private String title;
    private List<Author> authorList;
    private LocalDate publishedDate;
    private LocalDate ocDate;
    private LocalDate evaluatedDate;
    private LocalDate approvedDate;
    private List<DataUsage> dataUsage;

    @BsonIgnore
    public Boolean isDescribingNeurons() {
        return this.dataUsage.contains(DataUsage.DESCRIBING_NEURONS);
    }

    public ArticleData() {
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        if (isValidValue(pmid)) {
            this.pmid = pmid;
        }
    }

    public String getPmcid() {
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        if (isValidValue(pmcid)) {
            this.pmcid = pmcid;
        }
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        if (isValidValue(doi)) {
            this.doi = doi;
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if (isValidValue(link)) {
            this.link = link;
        }
    }

    public String getJournal() {
        return journal;

    }

    public void setJournal(String journal) {
        if (isValidValue(journal)) {
            this.journal = journal;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (isValidValue(title)) {
            this.title = title;
        }
    }

    public LocalDate getOcDate() {
        return ocDate;
    }

    public void setOcDate(LocalDate ocDate) {
        if (isValidValue(ocDate)) {
            this.ocDate = ocDate;
        }
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        if (isValidValue(publishedDate)) {
            this.publishedDate = publishedDate;
        }
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        List<Author> newAuthorList = new ArrayList<>();
        if (isValidValue(authorList)) {
            for (Author author : authorList) {
                Author newAuthor = new Author(author.getName(), author.getEmail());
                newAuthorList.add(newAuthor);
            }
            this.authorList = newAuthorList;
        }
    }

    public LocalDate getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(LocalDate evaluatedDate) {
        if (isValidValue(evaluatedDate)) {
            this.evaluatedDate = evaluatedDate;
        }
    }


    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        if (isValidValue(approvedDate)) {
            this.approvedDate = approvedDate;
        }
    }

    public List<DataUsage> getDataUsage() {
        return dataUsage;
    }

    public void setDataUsage(List<DataUsage> dataUsage) {
        this.dataUsage = dataUsage;
    }

    public Boolean isValidValue(Object value) {
        return value != null && !value.equals("");
    }

    @BsonIgnore
    public Boolean hasDoi() {
        return this.doi != null;
    }

    @BsonIgnore
    public Boolean hasPmid() {
        return this.pmid != null;
    }

    @BsonIgnore
    protected Boolean hasPmcid() {
        return this.pmcid != null;
    }

    @BsonIgnore
    protected Boolean hasPublishedDate() {
        return this.publishedDate != null;
    }

    @BsonIgnore
    protected Boolean hasJournal() {
        return this.journal != null && !this.journal.endsWith("…") && !this.journal.equals("bioRxiv");
    }

    @BsonIgnore
    protected Boolean hasTitle() {
        return !this.title.endsWith("…");
    }

    @BsonIgnore
    protected Boolean hasAuthorList() {
        return this.authorList != null && this.authorList.size() > 0;
    }

    @BsonIgnore
    protected Boolean similarJournal(String journal) {
        return this.journal.equals(journal) || this.journal.endsWith("…") || journal.endsWith("…");
    }

    @BsonIgnore
    private Float distanceString(String string1, String string2) {
        JaroWinklerDistance jwDistance = new JaroWinklerDistance();
        Float distance = jwDistance.getDistance(string1.toLowerCase(), string2.toLowerCase());
        log.debug("String1=" + string1);
        log.debug("String2=" + string2);
        log.debug("Jaro distance=" + distance);
        return distance;
    }

    @BsonIgnore
    private Boolean containsString(String string1, String string2) {
        String result1 =string1.replace("…", "");
        String result2 = string2.replace("…", "");
        return result1.contains(result2) || result1.contains(result2);
    }

    @BsonIgnore
    private Boolean similarDate(LocalDate date1, LocalDate date2, Integer days) {
        try {
            Period period = Period.between(date1, date2);
            int diff = period.getDays();
            log.debug("Date1=" + date1.toString());
            log.debug("Date2=" + date2.toString());
            log.debug("Same=" + (date1.getYear() == date2.getYear() || diff < days));
            return date1.getYear() == date2.getYear() || diff < days;
        } catch (NullPointerException ex) {
            return Boolean.FALSE;
        }
    }

    @BsonIgnore
    private Boolean sameValue(String value1, String value2) {
        Boolean result = Boolean.TRUE;
        if (this.isValidValue(value1) && this.isValidValue(value2)) {
            result = Boolean.FALSE;
        }
        return result;
    }

    @BsonIgnore
    private Boolean similarAuthorList(List<Author> authorList1, List<Author> authorList2) {
        Boolean result = Boolean.TRUE;

        try {
            for (Integer i = 0; i < authorList1.size(); i++) {
                authorList1.get(i).sameLastName(authorList2.get(i));
                result = Boolean.FALSE;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            //it doesn't matter if authorList length is not the same
        }
        log.debug("AuthorList1=" + authorList1.toString());
        log.debug("AuthorList2=" + authorList2.toString());
        log.debug("Same=" + result);

        return result;
    }

    @BsonIgnore
    public Integer getTitleCountTokens() {
        StopWords stopWords = new StopWords();
        List<String> titleNoStopWords = stopWords.removeStopWords(this.title);
        return titleNoStopWords.size();
    }

    @BsonIgnore
    public boolean similarData(ArticleData data, Double score) {
        Integer wordCount = this.getTitleCountTokens();
        Double maxScore = (wordCount * 0.5) + 0.5;
        Double match = score / maxScore;
        log.debug("Title1=" + this.title);
        log.debug("Title2=" + data.title);
        log.debug("Mongo {match=" + match + ", distance=" + score + ", max score=" + maxScore + "}");
        Float distance = this.distanceString(this.title, data.title);
        return ((match > 0.99 
                && (this.sameValue(pmid, data.pmid) || this.sameValue(doi, data.doi))//same title
                || (distance > 0.99 
                && (this.sameValue(pmid, data.pmid) || this.sameValue(doi, data.doi))))
                || (match > 0.90 //Same article
                && (this.distanceString(this.journal, data.journal) > 0.85
                && this.similarDate(this.publishedDate, data.publishedDate, 180)
                && this.similarAuthorList(this.authorList, data.authorList)
                && (this.sameValue(pmid, data.pmid) || this.sameValue(doi, data.doi))))
                || (distance > 0.90 //Incomplete article subset
                && this.similarDate(this.publishedDate, data.publishedDate, 390)
                && this.containsString(this.journal, data.journal)
                && this.containsString(this.title, data.title)
                && this.similarAuthorList(this.authorList, data.authorList))
        );
    }

    @BsonIgnore
    public Boolean isEmptyEvaluatedDate() {
        return this.evaluatedDate == null;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "pmid='" + pmid + '\'' +
                ", pmcid='" + pmcid + '\'' +
                ", doi='" + doi + '\'' +
                ", link='" + link + '\'' +
                ", journal='" + journal + '\'' +
                ", title='" + title + '\'' +
                ", authorList=" + authorList +
                ", publishedDate=" + publishedDate +
                ", ocDate=" + ocDate +
                ", evaluatedDate=" + evaluatedDate +
                ", approvedDate=" + approvedDate +
                ", dataUsage=" + dataUsage +
                '}';
    }

    public enum DataUsage {
        USING("Using"),
        DESCRIBING_NEURONS("Describing"),
        CITING("Citing"),
        ABOUT("About"),
        SHARING("Sharing");


        private final String usage;

        private DataUsage(String usage) {
            this.usage = usage;
        }

        public static DataUsage getUsage(String value) {
            for (DataUsage v : values()) {
                if (v.getUsage().equalsIgnoreCase(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }

        public String getUsage() {
            return usage;
        }

        public Boolean isDescribingNeurons() {
            return this.equals(DataUsage.DESCRIBING_NEURONS);
        }
    }
}
