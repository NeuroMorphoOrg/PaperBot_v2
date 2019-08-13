/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.neuromorpho.paperbot.search.communication.article.ArticleAssembler;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.dto.article.Author;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PortalSearchWileyService extends PortalSearch {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private ArticleAssembler assembler = new ArticleAssembler();

    @Value("${userAgent}")
    private String USER_AGENT;
    
    @Override
    protected void searchPage() throws IOException, InterruptedException {

        String urlFinal = null;
        List<String> queryParameterList = new ArrayList<>();
        queryParameterList.add("text1=" + this.keyWord.replace(" ", "+"));
        queryParameterList.add("field1=AllField");
        queryParameterList.add("AfterMonth=" + this.startDate.getMonthValue());
        queryParameterList.add("AfterYear=" + this.startDate.getYear());
        String queyParamsStr = "";
        for (String queryParameter : queryParameterList) {
            queyParamsStr = queyParamsStr + "&" + queryParameter;
        }
        urlFinal = this.portal.getUrl() + "?" + queyParamsStr;

        Thread.sleep(1 * 1000);
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        
        log.debug("Accessing portal url: " + urlFinal);
        Connection.Response response  = Jsoup.connect(urlFinal)
                .timeout(100 * 1000)
                .followRedirects(true)
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .referrer("http://www.google.com")
                .userAgent(USER_AGENT).execute();
        
        Integer it = 0;
        while (response.statusCode() != 200 && it < 10){
            it ++;
            response  = Jsoup.connect(urlFinal)
                    .timeout(100 * 1000)
                    .followRedirects(true)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .referrer("http://www.google.com")
                    .userAgent(USER_AGENT).execute();
        }
        this.searchDoc = response.parse();

    }

    @Override
    public ArticleContentDto getArticleContent(String doi, Portal portal) {
        return null;
    }

    @Override
    protected Elements findArticleList() {
        Elements articleList = this.searchDoc.select("div[class=item__body]");
        log.debug("Page with #articles: " + articleList.size());
        return articleList;
    }

    @Override
    protected String fillTitle(Element articleData) {
        Element elem = articleData.select("span[class=hlFld-Title] > a").first();
        String articleLink = elem.attr("href");
        this.article.setTitle(elem.text());
        return this.portal.getBase() + articleLink;
    }

    @Override
    protected void fillJournal(Element articleData, Element articlePage) {
        Element elem = articleData.select("a[class=publication_meta_serial]").first();
        String journal = elem.text();
        this.article.setJournal(journal);
    }

    @Override
    protected void fillAuthorList(Element articleData, Element articlePage) {

        List<Author> authorList = new ArrayList();
        Elements elemList = articleData.select("li > span[class=hlFld-ContribAuthor] > a");
        for (Element elem : elemList) {
            Author author = new Author(elem.text(), null);
            authorList.add(author);
        }
        this.article.setAuthorList(authorList);

    }

    @Override
    protected void fillPublishedDate(Element articleData, Element articlePage) {
        try {
            Element elem = articleData.select("p[class=meta__epubDate]").first();
            String date = elem.text().replace("First published: ", "");
            this.article.setPublishedDate(assembler.tryParseDate(date));
        } catch (Exception ex) {
            log.error("Exception: error reading date");
        }
    }

    @Override
    protected void fillDoi(Element articleData, Element articlePage) {
        Element elem = articleData.select("span[class=hlFld-Title] > a").first();
        String doi = elem.attr("href").replace("/doi/", "");
        article.setDoi(doi);
    }

    @Override
    protected void fillLinks(Element articleData, Element articlePage) {
    }
    
    @Override
    protected Boolean loadNextPage() {
        Boolean nextPage = Boolean.FALSE;
        try {
            Element selected = this.searchDoc.select("div[class=pagination] a[title=Next page]").first();
            if (selected == null) {
                return nextPage;
            }
            String link = selected.attr("href");
            log.debug("Accessing portal url next page: " + link);

            this.searchDoc = Jsoup.connect(link)
                    .timeout(30 * 1000)
                    .followRedirects(true)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").get();
            nextPage = Boolean.TRUE;

        } catch (IOException ex) {
            log.error("Exception loading next page", ex);
        }
        return nextPage;
    }

    @Override
    protected void searchForTitlesApi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
