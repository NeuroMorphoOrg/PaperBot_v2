package org.neuromorpho.paperbot.search.communication.article;


import org.neuromorpho.paperbot.search.dto.article.Article;
import org.neuromorpho.paperbot.search.dto.article.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LiteratureConnection {

    @Value("${uriArticles}")
    private String uri;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ArticleResponse saveArticle(Article article, String collection) {
        try {
            String url = uri + "/" + collection;
            log.debug("Creating rest connection for URI: " + url);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ArticleResponse> response = restTemplate.postForEntity(
                    url, article, ArticleResponse.class);
            return response.getBody();
        } catch (Exception ex) {
            log.error("Error: ", ex);
        }
        return null;
    }

    /*
    Move 2 Inaccessible disabled
     */
    public void updateCollection(String id, String oldCollection, String newCollection) {
        String url = uri + "/status/" + id + "&newArticleStatus=" + newCollection;
        log.debug("Creating rest connection for URI: " + url);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, null);
    }

    public void saveSearchPortal(String id, String source, String keyWord) {

        String url = uri + "/" + id + "/searchPortal";
        log.debug("Creating rest connection for URI: " + url);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(url, new Search(source, keyWord));
    }


}
