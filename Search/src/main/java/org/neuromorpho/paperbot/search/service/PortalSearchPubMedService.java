/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PortalSearchPubMedService extends PortalSearch {

    private final static Logger log = LoggerFactory.getLogger(PortalSearchPubMedService.class);

    @Override
    public void searchForTitlesApi() throws InterruptedException {

        try {
            
            RestTemplate restTemplate = new RestTemplate();
            String uri = this.portal.getSearchUrlApi() + "/esearch.fcgi?"
                    + "db=" + this.portal.getDb()
                    + "&apiKey=" + this.portal.getToken()
                    + "&retmode=json"
                    + "&retmax=5000"
                    + "&term=" + this.keyWord
                    + " AND " + this.startDate.getYear() + ":"
                    + this.endDate.getYear() + "[pdat]";
            log.debug("PubMed retrieving from URI: " + uri);

            Map<String, Object> pmidMap = restTemplate.getForObject(uri, Map.class);

            Map result = (HashMap) pmidMap.get("esearchresult");
            ArrayList<String> uidList = (ArrayList) result.get("idlist");
            log.debug("Articles Found : " + uidList.size());

            for (String uid : uidList) {
                try {
                    log.debug("Seconds to sleep: " + 1);
                    log.debug("......................................");
                    Thread.sleep(1 * 1000);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    this.inaccessible = Boolean.FALSE;
                    article = pubMedConnection.findArticleFromPMID(uid, this.portal.getDb());
                    this.saveArticle();
                    log.debug("DB: " + this.portal.getDb() + " PMID: " + uid);


                } catch (HttpServerErrorException ex) {
                    log.error("Exception: ", ex);
                }

            }
        } catch (Exception ex) {
            log.error("Exception: ", ex);
        }
    }

    @Override
    public ArticleContentDto getArticleContent(String doi, Portal portal) {
        return null;
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

}
