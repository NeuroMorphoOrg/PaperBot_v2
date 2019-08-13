/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neuromorpho.paperbot.search.communication.article.ArticleAssembler;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.dto.article.Article;
import org.neuromorpho.paperbot.search.dto.article.Author;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PortalSearchSpringerLinkService extends PortalSearch {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private ArticleAssembler assembler = new ArticleAssembler();

    @Override
    public void searchForTitlesApi() throws InterruptedException {
        Integer page = 1;
        RestTemplate restTemplate = new RestTemplate();

        /*
         * Springer incorrectly returning media type html inestead of json
         */
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        // Add JSON message handler
        MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList();
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        // Add default media type in case marketplace uses incorrect MIME type, otherwise
        // Spring refuses to process it, even if its valid JSON
        json.setSupportedMediaTypes(supportedMediaTypes);
        mc.add(json);
        restTemplate.setMessageConverters(mc);
        /*
        * Springer incorrectly returning media type html inestead of json
         */
        log.debug("Seconds to sleep: " + 10);
        log.debug("......................................");
        Thread.sleep(1 * 1000);
        Integer iterations = 0;
        do {//iterate over pages
            String uri = this.portal.getSearchUrlApi()
                    + "q=(" + this.keyWord
                    + " AND year:" + this.startDate.getYear()
                    + ")&s=" + page
                    + "&api_key=" + this.portal.getToken();
            log.debug("API retrieving from URI: " + uri);
            page++;
            Map<String, Object> result = restTemplate.getForObject(uri, Map.class);

            ArrayList<Map> nResultsMap = (ArrayList) result.get("result");
            String totalStr = (String) nResultsMap.get(0).get("total");
            Integer total = Integer.parseInt(totalStr);
            String pageLengthStr = (String) nResultsMap.get(0).get("pageLength");
            Integer pageLength = Integer.parseInt(pageLengthStr);
            iterations = total / pageLength;
            log.debug("Articles Found : " + total);

            ArrayList<Map> infoList = (ArrayList) result.get("records");
            for (Map info : infoList) {
                article = new Article();
                String title = (String) info.get("title");
                article.setTitle(title);
                article.setDoi((String) info.get("doi"));
                article.setJournal((String) info.get("publicationName"));

                ArrayList<Map> urlListMap = (ArrayList) info.get("url");
                article.setLink((String) urlListMap.get(0).get("value"));

                String dateStr = (String) info.get("publicationDate");
                article.setPublishedDate(assembler.tryParseDate(dateStr));

                ArrayList<Map> authorListMap = (ArrayList) info.get("creators");

                List<Author> authorList = new ArrayList();
                for (Map authorMap : authorListMap) {
                    String completeName = (String) authorMap.get("creator");
                    String[] name = completeName.split(", ");
                    if (name.length < 2) {
                        name = completeName.split(" ");
                    }
                    Author author = new Author(name[1] + " " + name[0], null);

                    authorList.add(author);
                }
                article.setAuthorList(authorList);
                
                this.saveArticle();
            }
        } while (iterations > 0 && iterations >= page - 1);

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
