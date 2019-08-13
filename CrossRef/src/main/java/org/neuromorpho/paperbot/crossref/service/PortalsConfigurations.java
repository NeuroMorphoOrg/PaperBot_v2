package org.neuromorpho.paperbot.crossref.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class PortalsConfigurations {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String prepareHighWireLink(Map message){
        String finalUrl = null;
        String url = (String) message.get("URL");
        //read redirect from URL
        try {
            finalUrl = this.getFinalUrl(url);
            log.debug("Final URL to access after redirects: " + finalUrl);
            return  finalUrl + ".full.pdf";
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        return null;
    }

    public String prepareFrontiersLink(Map message){
        String finalUrl = null;
        String url = (String) message.get("URL");
        //read redirect from URL
        try {
            finalUrl = this.getFinalUrl(url);
            log.debug("Final URL to access after redirects: " + finalUrl);
            return  finalUrl + ".full.pdf";
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        return null;
    }
    
    
    private String getFinalUrl(String url) throws IOException {
        String response = null;
        String finalResponse = null;

        URL urlW = new URL(url);
        HttpURLConnection con = (HttpURLConnection)(urlW.openConnection());
        con.setInstanceFollowRedirects(false);
        con.connect();
        response = con.getHeaderField("Location");
        if (response != null){
            finalResponse = response;
        }
        while (con.getResponseCode() == 302){
            if (!response.contains("http")){
                response = urlW.getProtocol() + "://" + urlW.getAuthority() + response;
            }
            if (response != null){
                finalResponse = response;
            }
            log.debug("Response: " + con.getResponseCode() + " Redirect URL: " + response);
            urlW = new URL(response);
            con = (HttpURLConnection)(urlW.openConnection());
            con.setInstanceFollowRedirects(false);
            con.connect();
            response = con.getHeaderField("Location");
           
        }
        return finalResponse;
    }
    
    
}
