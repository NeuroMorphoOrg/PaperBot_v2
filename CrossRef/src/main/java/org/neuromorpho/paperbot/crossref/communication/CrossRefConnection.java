package org.neuromorpho.paperbot.crossref.communication;

import java.util.HashMap;
import java.util.Map;

import org.neuromorpho.paperbot.crossref.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrossRefConnection {

    @Value("${uri}")
    private String uri;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Map findMetadataFromDOI(String doi) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = uri
                + "works/" + doi;
        log.debug("Accesing crossRef using url: " + url);

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            Map<String, Object> articleMap = response.getBody();
            return (HashMap) articleMap.get("message");
        } else if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())){
            throw new NotFoundException("DOI not found in CrossRef");
        }
        else {
             throw new Exception("UnknowException accessing CrossRef");
        }
    }

}
