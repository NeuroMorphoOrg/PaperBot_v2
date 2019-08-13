package org.neuromorpho.paperbot.search.communication.pubmed;

import org.neuromorpho.paperbot.search.dto.article.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PubMedConnection {

    @Value("${uriPubMed}")
    private String uri;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Identifiers findIdentifiersFromTitle(String title, String db) {
        try {
            String url = uri + "/identifiers?title=" + title + "&db=" + db;
            log.debug("Creating rest connection for URI: " + url);
            RestTemplate restTemplate = new RestTemplate();
            Identifiers response = restTemplate.getForObject(url, Identifiers.class);
            return response;
        } catch (Exception ex) {
            log.error("PubMed connection exception", ex);
        }
        return null;
    }

    public Article findArticleFromPMID(String pmid, String db) {
        try {

            String url = uri + "?pmid=" + pmid + "&db=" + db;
            log.debug("Creating rest connection for URI: " + url);
            RestTemplate restTemplate = new RestTemplate();
            Article response = restTemplate.getForObject(url, Article.class);
            return response;
        } catch (Exception ex) {
            log.error("PubMed connection exception", ex);
        }
        return null;

    }

}
