package org.neuromorpho.paperbot.crossref.controller;

import org.neuromorpho.paperbot.crossref.model.Article;
import org.neuromorpho.paperbot.crossref.service.CrossRefService;
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
public class CrossRefController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrossRefService crossRefService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home() {
        return "CrossRef up & running!";
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Article retrieveCrossRefArticleData(
            @RequestParam(required = true) String doi) throws Exception {
        log.debug("Calling crossref with doi: " + doi);
        Article article = crossRefService.retrieveArticleData(doi);
        log.debug("Data from crossef: " + article.toString());
        return article;

    }
    

}
