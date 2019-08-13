package org.neuromorpho.paperbot.search.controller;

import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.neuromorpho.paperbot.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchService searchService;

    @CrossOrigin
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void launchSearch() throws Exception {
        log.debug("Launching full search");
        searchService.launchSearch();
    }

    @CrossOrigin
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ArticleContentDto getArticleContent(
            @RequestParam String doi,
            @RequestParam String portalName) throws Exception {
        return searchService.getArticleContent(doi, portalName);
    }


}
