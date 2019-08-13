package org.neuromorpho.paperbot.search.service;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.neuromorpho.paperbot.search.communication.article.ArticleResponse;
import org.neuromorpho.paperbot.search.communication.article.LiteratureConnection;
import org.neuromorpho.paperbot.search.communication.pubmed.Identifiers;
import org.neuromorpho.paperbot.search.communication.pubmed.PubMedConnection;
import org.neuromorpho.paperbot.search.exception.MissingDataException;
import org.neuromorpho.paperbot.search.model.KeyWord;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.dto.article.Article;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class PortalSearch implements IPortalSearch {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected Document searchDoc;
    protected Portal portal;
    protected String keyWord;
    protected String collection;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Boolean inaccessible;
    protected Article article;

    @Autowired
    protected LiteratureConnection literatureConnection;
    @Autowired
    protected PubMedConnection pubMedConnection;

    @Override
    public void findArticleList(KeyWord keyWord, Portal portal) throws InterruptedException {
        try {
            this.portal = portal;
            this.keyWord = keyWord.getName();
            this.startDate = portal.getStartSearchDate();
            this.endDate = this.getSearchEndDate();
            this.collection = keyWord.getCollection();

            log.debug("Executing portal " + portal.getName() + " for keyword " + keyWord.getName());
            if (this.portal.hasSearchAPI()) {
                log.debug("Seconds to sleep: " + 1);
                log.debug("......................................");
                Thread.sleep(1 * 1000);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                this.searchForTitlesApi();
            } else {
                this.searchPage();
                this.searchForTitles();
            }
        } catch (HttpStatusException ex) { // if jsoup returns this exception, the page was empty
            log.error("Error", ex);
            //throw ex;

        } catch (IOException ex) { // if jsour returns this exception, the page was empty
            log.debug("Articles found 0 ");
            log.error("Error", ex);
        }

    }

    @Override
    public abstract ArticleContentDto getArticleContent(String doi, Portal portal);

    //to be override by the sons
    protected abstract Elements findArticleList();

    protected abstract void searchPage() throws InterruptedException, IOException;

    protected abstract Boolean loadNextPage() throws InterruptedException, IOException;

    protected abstract String fillTitle(Element articleData);

    protected abstract void fillPublishedDate(Element articleData, Element articlePage);

    protected abstract void fillJournal(Element articleData, Element articlePage);

    protected abstract void fillAuthorList(Element articleData, Element articlePage);

    protected abstract void fillDoi(Element articleData, Element articlePage);

    protected abstract void fillLinks(Element articleData, Element articlePage);


    protected void searchForTitles() throws InterruptedException, IOException {

        Elements articleList = this.findArticleList();
        for (Element articleElement : articleList) {
            this.createArticle(articleElement);
        }
        Boolean existsNextPage = this.loadNextPage();
        if (existsNextPage) {
            searchForTitles();
        }

    }

    protected void createArticle(Element articleData) throws InterruptedException {
        this.inaccessible = Boolean.FALSE;
        this.article = new Article();
        Integer i = 0;
        Boolean read = Boolean.FALSE;
        try {
            while (i < 10 && !read) {
                String articleLink = this.fillTitle(articleData);
                if (!this.article.getTitle().isEmpty()
                        && !containsHanScript(this.article.getTitle())) {
                    log.debug("Reading article: " + articleLink);
                    Element articlePage = null;
                    log.debug("Article title: " + this.article.getTitle());
                    log.debug("Article link: " + articleLink);
                    /*if (articleLink != null && !this.portal.getName().equals("GoogleScholar")) {
                        articleDoc = Jsoup.connect(articleLink)
                                .timeout(30 * 1000)
                                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36").get();
                        articlePage = articleDoc.select("html").first();
                    }*/

                    this.fillJournal(articleData, articlePage);
                    this.fillAuthorList(articleData, articlePage);
                    this.fillPublishedDate(articleData, articlePage);
                    this.fillDoi(articleData, articlePage);
                    if (!this.inaccessible) {
                        this.fillLinks(articleData, articlePage);
                    }
                    this.saveArticle();
                }
                read = Boolean.TRUE;
            }
        } catch (MissingDataException |NullPointerException ex) {
            log.debug(ex.getMessage());
        }
    }

    protected LocalDate getSearchEndDate() {
        return LocalDate.now();
    }


    /*
     * Contains Asian characters
     */
    private static Boolean containsHanScript(String s) {
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);
            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    protected abstract void searchForTitlesApi() throws InterruptedException;

    protected void saveArticle() throws InterruptedException {
        String db = "pubmed";
        if (article.getTitle() != null &&
                article.getAuthorList() != null &&
                article.getAuthorList().isEmpty()) {


            if (!this.portal.getName().equals("PubMed") && !this.portal.getName().equals("PubMedCentral")) {
                Identifiers identifiers = pubMedConnection.findIdentifiersFromTitle(this.article.getTitle(), db);
                if (identifiers != null) {
                    this.article.setPmid(identifiers.getPmid());
                    this.article.setPmcid(identifiers.getPmcid());
                    if (identifiers.getPmid() != null) {
                        log.debug("Seconds to sleep: " + 1);
                        log.debug("......................................");
                        Thread.sleep(1 * 1000);
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException();
                        }
                        Article articlePM = pubMedConnection.findArticleFromPMID(identifiers.getPmid(), db);
                        if (articlePM != null) {
                            this.article = articlePM;
                        }
                    }
                }
            }

            //  if (this.article.getPublishedDate() == null || (this.article.getPublishedDate() != null && this.article.getPublishedDate().after(this.portal.getStartSearchDate()))) {
            List<String> dataUsage = new ArrayList();
            if (this.collection.equals("Pending evaluation")) {
                dataUsage.add("Describing");
            } else {
                dataUsage.add("Citing");
            }
            this.article.setDataUsage(dataUsage);

            log.debug(this.article.toString());

            ArticleResponse response = literatureConnection.saveArticle(this.article, this.collection);
            if (response.getId() != null) {
                literatureConnection.saveSearchPortal(response.getId(), this.portal.getName(), this.keyWord);
            }
            log.debug("Seconds to sleep: " + 1);
            log.debug("......................................");
            Thread.sleep(1 * 1000);
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
        }

    }

}
