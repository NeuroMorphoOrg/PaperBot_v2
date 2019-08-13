package org.neuromorpho.paperbot.pubmed.controller;

import org.neuromorpho.paperbot.pubmed.model.Article;
import org.neuromorpho.paperbot.pubmed.service.PubMedService;
import org.neuromorpho.paperbot.pubmed.service.results.identifiers.Identifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class PubMedController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PubMedService pubMedService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home() {
        return "PubMed up & running!";
    }

    /*
     * Retrieves article data from PubMed based in the PMID provided
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Article retrievePubMedArticleData(
            @RequestParam(required = true) String pmid,
            @RequestParam(required = true) String db)
            throws Exception {
        log.debug("Calling pubmed with pmid: " + pmid);
        Article article = pubMedService.retrievePubMedArticleData(pmid, Article.DB.getDB(db));
        log.debug("Data from pubmed: " + article.toString());
        return article;

    }

    /*
     * Retrieves article identifiers from id & db
     */
    @CrossOrigin
    @RequestMapping(value = "/identifiers", method = RequestMethod.GET)
    public Identifiers retrievePMIDFromTitle(
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String db)
            throws Exception {
        log.debug("Calling pubmed with title: " + title + " and DB: " + db);
        return pubMedService.retrieveIdentifiersFromTitleDB(title, Article.DB.getDB(db));
    }

}
