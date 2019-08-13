/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neuromorpho.paperbot.search.communication.article.ArticleAssembler;
import org.neuromorpho.paperbot.search.dto.article.Author;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.neuromorpho.paperbot.search.model.Portal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PortalSearchGoogleScholarService extends PortalSearch {

    private final Integer maxMin = 10;
    private final Integer minMin = 2;
    private Integer i = 0;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    ArticleAssembler assembler = new ArticleAssembler();

    @Override
    protected void searchPage() throws InterruptedException, IOException {
        List<String> queryParameterList = new ArrayList<>();
        queryParameterList.add("as_ylo=" + this.startDate.getYear());
        queryParameterList.add("q=" + this.keyWord.replace(" ", "+"));
        queryParameterList.add("hl=en");
        queryParameterList.add("as_sdt=0,47");
        String queyParamsStr = "";
        for (String queryParameter : queryParameterList) {
            queyParamsStr = queyParamsStr + "&" + queryParameter;
        }
        String urlFinal = this.portal.getUrl() + "?" + queyParamsStr;
        log.debug("Accessing portal url: " + urlFinal);
        // so add 1 to make it inclusive
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        Integer randomNum = rand.nextInt((maxMin - minMin) + 1) + minMin;
        log.debug("Random minutes to sleep: " + randomNum);
        log.debug("......................................");
        if (i == 30) { //Every 30 calls sleep 4h
            log.debug("Sleeping 10 hours ");
            randomNum = 600;
            i = 0;
        }
        i++;
        Thread.sleep(randomNum * 60 * 1000);
        this.searchDoc = Jsoup.connect(urlFinal)
                .timeout(60 * 1000)
                .followRedirects(true)
                .header("Content-Type", "text/html; charset=UTF-8")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36").get();

    }

    @Override
    public ArticleContentDto getArticleContent(String doi, Portal portal) {
        return null;
    }

    @Override
    protected Elements findArticleList() {
        Elements articleList = this.searchDoc.select("div[id=gs_res_ccl_mid] > div[class=gs_r gs_or gs_scl]");
        log.debug("Articles found: " + articleList.size());
        return articleList;
    }

    @Override
    protected String fillTitle(Element articleData) {
        Element elem = articleData.select("h3[class=gs_rt] > a").first();
        String articleLink = elem.attr("href");
        this.article.setTitle(elem.text().trim());
        log.debug("Article title: " + this.article.getTitle());
        return articleLink;

    }

    @Override
    protected void fillJournal(Element articleData, Element articlePage) {
        try {
            Element elem = articleData.select("div[class=gs_a]").first();
            String metadata = elem.text().split(" - ")[1];
            this.article.setJournal(metadata.split(",")[0]);
        } catch (Exception e) {
            log.error("Error filling journal for article: " + this.article.getTitle(), e);
        }
    }

    @Override
    protected void fillAuthorList(Element articleData, Element articlePage) {
        try {
            List<Author> authorList = new ArrayList();
            Element elem = articleData.select("div[class=gs_a]").first();
            String metadata = elem.text().split(" - ")[0];
            String[] authorListStr = metadata.split(",");
            for (String authorStr : authorListStr) {
                String completeName = authorStr.replace("…", "").trim();
                Author author = new Author(completeName, null);
                authorList.add(author);

            }
            this.article.setAuthorList(authorList);
        } catch (Exception e) {
            log.error("Error filling authors for article: " + this.article.getTitle(), e);
        }
    }

    @Override
    protected void fillPublishedDate(Element articleData, Element articlePage) {
        try {
            Element elem = articleData.select("div[class=gs_a]").first();
            String metadata = elem.text().split(" - ")[1];
            String[] dateArray = metadata.split(",");
            String date;
            if (dateArray.length == 1) {
                date = dateArray[0];
            } else {
                date = dateArray[1];
            }
            this.article.setPublishedDate(assembler.tryParseDate(date));
        } catch (Exception e) {
            log.error("Error filling date for article: " + this.article.getTitle(), e);
        }
    }

    @Override
    protected void fillDoi(Element articleData, Element articlePage) {
        //No DOI
    }

    @Override
    protected void fillLinks(Element articleData, Element articlePage) {
        Element elem = articleData.select("h3[class=gs_rt] > a").first();
        this.article.setLink(elem.attr("href"));
    }


    @Override
    protected Boolean loadNextPage() throws InterruptedException, IOException {
        Boolean nextPage = Boolean.FALSE;
        Element linkList = this.searchDoc.select("div[id=gs_n] td[align=left] > a").first();
        if (linkList != null) {
            //simulate human behaviour sleep randome minutes between labor hours
            Random rand = new Random();
            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            
            Integer randomNum = rand.nextInt((maxMin - minMin) + 1) + minMin;
            if (i == 30) { //Every 30 calls sleep 4h
                log.debug("Sleeping 10 hours ");
                randomNum = 600;
                i = 0;
            }
            i++;
            log.debug("Random minutes to sleep: " + randomNum);
            log.debug("......................................");
            Thread.sleep(randomNum * 60 * 1000);
            String link = linkList.attr("href");
            log.debug("Loading next page: " + this.portal.getBase() + link);

            this.searchDoc = Jsoup.connect(this.portal.getBase() + link)
                    .timeout(30 * 1000)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").get();
            nextPage = Boolean.TRUE;

        }

        return nextPage;
    }


    @Override
    protected void searchForTitlesApi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
