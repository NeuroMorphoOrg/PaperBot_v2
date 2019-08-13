/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service.sciencedirect;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neuromorpho.paperbot.search.communication.article.ArticleAssembler;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext.FullText;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.search.Entry;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.search.Link;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.search.SearchResults;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDtoAssembler;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.service.PortalSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PortalSearchScienceDirectService extends PortalSearch {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void searchForTitlesApi() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ArticleAssembler assembler = new ArticleAssembler();
        Integer ocurrences = StringUtils.countMatches(this.keyWord, "\"");
        String replacedKeyword = this.keyWord;
        for (Integer i = 0; i < ocurrences / 2; i++) {
            replacedKeyword = replacedKeyword.replaceFirst("\"", "%7B");
            replacedKeyword = replacedKeyword.replaceFirst("\"", "%7D");
        }

        String year = Integer.toString(this.endDate.getYear());
        if (this.startDate.getYear() != this.endDate.getYear()){
            year = this.startDate.getYear() + "-" + this.endDate.getYear();
        }
        String uri = this.portal.getSearchUrlApi()
                + "query=" + replacedKeyword
                + "&date=" + year 
                //+ "&start=" + startRecord
                + "&apiKey=" + this.portal.getToken();
        Boolean next = Boolean.FALSE;
        do {//iterate over pages

            log.debug("API retrieving from URI: " + uri);
            try {

                SearchResults searchResults = restTemplate.getForObject(new URI(uri), SearchResults.class);
                next = Boolean.FALSE;

                if (!searchResults.isEmpty()) {
                    log.debug("Articles Found : " + searchResults.getResultsSize());

                    for (Entry entry : searchResults.getEntryList()) {
                        this.article = assembler.createArticle(entry);
                        this.saveArticle();
                    }
                    for (Link link : searchResults.getLinkList()) {
                        if (link.existsNextPage()) {
                            uri = link.getLink();
                            next = Boolean.TRUE;
                        }
                    }
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } while (next);


    }

    @Override
    protected Elements findArticleList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void searchPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Boolean loadNextPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String fillTitle(Element articleData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillPublishedDate(Element articleData, Element articlePage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillJournal(Element articleData, Element articlePage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillAuthorList(Element articleData, Element articlePage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillDoi(Element articleData, Element articlePage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillLinks(Element articleData, Element articlePage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void searchForTitles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArticleContentDto getArticleContent(String doi, Portal portal) {
        this.portal = portal;
        ArticleContentDtoAssembler assembler = new ArticleContentDtoAssembler();
        return assembler.createArticleContentDto(this.getFullText(doi), this.getPDF(doi),  "&apiKey=" + portal.getToken());
    }

    protected FullText getFullText(String doi) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String uri = portal.getContentUrlApi()
                    + doi
                    + "?apiKey=" + this.portal.getToken();

            log.debug("API retrieving from URI full text: " + uri);
            return restTemplate.getForObject(uri, FullText.class);
        }catch (HttpClientErrorException ex) {
            log.error("Error retrieving full text", ex);
            return null;
        }
    }

    
    protected String getPDF(String doi) {

        return portal.getContentUrlApi()
                + doi
                + "?apiKey=" + this.portal.getToken()
                + "&httpAccept=application/pdf";
    }


}
