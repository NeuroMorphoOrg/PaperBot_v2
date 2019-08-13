package org.neuromorpho.paperbot.pubmed.service;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.neuromorpho.paperbot.pubmed.exceptions.PubMedException;
import org.neuromorpho.paperbot.pubmed.service.results.identifiers.Identifiers;
import org.neuromorpho.paperbot.pubmed.service.results.identifiers.Record;
import org.neuromorpho.paperbot.pubmed.model.Article;
import org.neuromorpho.paperbot.pubmed.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

@Service
public class PubMedService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${uri}")
    private String uri;
    @Value("${uri2}")
    private String uri2;
    @Value("${apiKey}")
    private String apiKey;

    public Article retrievePubMedArticleData(String pmid, Article.DB db) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String url = uri
                + "/esummary.fcgi?"
                + "db=" + db.getDB()
                + "&api_key=" + apiKey
                + "&retmode=json"
                + "&id=" + pmid;
        log.debug("Accesing pubmed using url: " + url);

        Map<String, Object> articleMap = restTemplate.getForObject(url, Map.class);
        Article article = new Article();
        Map result = (HashMap) articleMap.get("result");
        if (result == null) {
            List<String> error = (List) articleMap.get("esummaryresult");
            throw new PubMedException(error.get(0));
        }
        ArrayList<String> uids = (ArrayList) result.get("uids");
        if (uids.isEmpty()) {
            throw new Exception("Unknown pmid not found in " + db + " id: " + pmid);
        }
        Map articleValues = (HashMap) result.get(uids.get(0));
        String title = (String) articleValues.get("title");
        article.setTitle(this.getCorrectedName(title));
        Identifiers record = this.retrieveIdentifiers(pmid, db);

        if (db.equals(Article.DB.PUBMED)) {
            article.setPmid(pmid);
            article.setPmcid(record.getPmcid());
        } else {
            article.setPmcid(pmid);
            article.setPmid(record.getPmid());
        }
        
        article.setJournal(this.getCorrectedName((String) articleValues.get("fulljournalname")));
        ArrayList<Map> articleIds = (ArrayList) articleValues.get("articleids");
        for (Map articleId : articleIds) {
            if (articleId.get("idtype").equals("doi")) {
                article.setDoi((String) articleId.get("value"));
                break;
            }
        }
        String sortDateStr = (String) articleValues.get("epubdate");
        if (sortDateStr.isEmpty()){
            sortDateStr = (String) articleValues.get("pubdate");
        }
        
        article.setPublishedDate(this.tryParseDate(sortDateStr));
        
        List<Author> authorList = new ArrayList();

        if (db.equals(Article.DB.PUBMED)) {
            url = uri
                    + "/efetch.fcgi?"
                    + "db=" + db.getDB()
                    + "&api_key=" + apiKey
                    + "&id=" + pmid
                    + "&retmode=xml";
            log.debug("Accesing pubmed authors information & affiliation using url: " + url);

            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            String xml = restTemplate.getForObject(url, String.class);
            Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

            for (Element a : doc.select("Author")) {
                Element e = a.select("Affiliation").first();
                String email = null;
                if (e != null && e.text().contains("@")) {
                    email = e.text().substring(e.text().lastIndexOf(" ") + 1, e.text().length());
                    if (email.endsWith(".")) {
                        email = email.substring(0, email.length() - 1);
                        log.debug("Email fo author: " + email);
                    }

                }
                Element fn = a.select("ForeName").first();
                Element ln = a.select("LastName").first();
                if (fn != null && ln != null) {
                  /*  if (email != null && !email.contains(ln.text())) {
                        email = null;
                    }*/
                    Author author = new Author(fn.text() + " " + ln.text(), email);
                    authorList.add(author);
                }
            }
        } else {
            ArrayList<Map> authors = (ArrayList) articleValues.get("authors");
            for (Map authorStr : authors) {
                if (authorStr.get("authtype").equals("Author")) {
                    String name = (String) authorStr.get("name");
                    String finalName = name;
                    if (name.contains(" ")) {
                        String[] nameArray = name.split(" ");
                        finalName = nameArray[1] + " " + nameArray[0];
                    }
                    Author author = new Author(finalName, null);

                    authorList.add(author);
                }
            }

        }
        article.setAuthorList(authorList);
        return article;
    }

    public Identifiers retrieveIdentifiers(String id, Article.DB db) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        if (db.equals(Article.DB.PUBMEDCENTRAL)) {
            id = "PMC" + id;
        }
        String url = uri2 + "/idconv/v1.0/?"
                + "ids=" + id
                + "&api_key=" + apiKey
                + "&format=json";
        log.debug("Accessing " + url);

        Record record = restTemplate.getForObject(
                url,
                Record.class);
        return record.getIdentifiersList().get(0);

    }


    public Identifiers retrieveIdentifiersFromTitleDB(String title, Article.DB db) throws Exception {
        String pmid = null;
        RestTemplate restTemplate = new RestTemplate();

        String url = uri + "/esearch.fcgi?"
                + "db=" + db.getDB()
                + "&api_key=" + apiKey
                + "&retmode=json"
                + "&term=" + "\"" + title + "\""
                + "&field=title" + "\"";
        log.debug("Accessing " + url);

        Map<String, Object> pmidMap = restTemplate.getForObject(
                url,
                Map.class);
        Map result = (HashMap) pmidMap.get("esearchresult");
        ArrayList<String> uidList = (ArrayList) result.get("idlist");

        if (uidList.size() == 1){
            pmid = uidList.get(0);
        }
       /* else {
            for (String uid : uidList) {
                String xmlUri = uri + "/esummary.fcgi?"
                        + "db=" + db.getDB()
                        + "&api_key=" + apiKey
                        + "&retmode=xml"
                        + "&version=2.0"
                        + "&id=" + uid;
                log.debug("Accessing " + xmlUri);

                String articleXML = restTemplate.getForObject(
                        xmlUri,
                        String.class);
                String correctedTitle = this.getTitle(articleXML);
                String possibleTitle = this.getCorrectedName(correctedTitle);
                JaroWinklerDistance jwDistance = new JaroWinklerDistance();

                Float distance = jwDistance.getDistance(possibleTitle, title);
                log.debug("possible match title: " + possibleTitle
                        + " JaroWinklerDistance to the real title: " + distance);

                if (distance >= 0.9) {
                    pmid = uid;
                }
            }
        }*/
        if (pmid != null) {
            return this.retrieveIdentifiers(pmid, db);
        }
        return null;
    }

    private String getTitle(String xml) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        InputSource source = new InputSource(new StringReader(xml));
        return xpath.evaluate("//Title", source);
    }

    private String getCorrectedName(String name) {
        if (name != null && name.endsWith(".")) {
            name = name.substring(0, name.length() - 1);
        }
        return name.replaceAll("&amp;", "&").replaceAll("<[^>]+>", "");
    }

    protected LocalDate tryParseDate(String dateStr) {

        String[] formatStrings = {"yyyy MMM d", "yyyy MMM dd", "yyyy MMM", "yyyy"};
        for (String formatString : formatStrings) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern(formatString)
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter();
            try {
                LocalDate localDate = LocalDate.parse(dateStr.trim(), formatter);
                return localDate;
            } catch (DateTimeParseException ex) {
                log.error("Error parsing date: ", ex);
            }
        }
        return null;
    }
    
}
