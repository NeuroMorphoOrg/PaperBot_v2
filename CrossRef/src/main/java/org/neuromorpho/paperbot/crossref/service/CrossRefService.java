package org.neuromorpho.paperbot.crossref.service;


import org.neuromorpho.paperbot.crossref.communication.CrossRefConnection;
import org.neuromorpho.paperbot.crossref.model.Article;
import org.neuromorpho.paperbot.crossref.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrossRefService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    

    @Value("${folder}")
    private String folder;
    
    @Autowired
    protected CrossRefConnection crossRefConnection;

    public Article retrieveArticleData(String doi) throws Exception {
        Article article = new Article();
        Map message = crossRefConnection.findMetadataFromDOI(doi);
        log.debug("Message from CrossRef: " + message);
        List<String> titles = (ArrayList) message.get("title");
        article.setTitle(titles.get(0));

        ArrayList<Map> authors = (ArrayList) message.get("author");
        List<Author> authorList = new ArrayList();
        if (authors != null) {
            for (Map a : authors) {
                String given = (String) a.get("given");
                String family = (String) a.get("family");
                Author author = new Author(given + " " + family, null);
                authorList.add(author);
            }
        }
        article.setAuthorList(authorList);
        List<String> journalList = (List) message.get("container-title");
        if (journalList != null && journalList.size() > 0) {
            article.setJournal(journalList.get(0));
        } else {
            Map institution = (Map) message.get("institution");
            String name = (String) institution.get("name");
            article.setJournal(name);
        }
        Map created = (Map) message.get("created");
        String sortDateStr = (String) created.get("date-time");
        article.setPublishedDate(this.tryParseDate(sortDateStr));
        article.setLink((String) message.get("URL"));
        article.setDoi(doi);
        log.debug("Article found: " + article.toString());
        return article;
    }

    protected LocalDate tryParseDate(String dateStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'");
        return LocalDate.parse(dateStr, formatter);
    }



}
