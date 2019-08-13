package org.neuromorpho.paperbot.search.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.neuromorpho.paperbot.search.exception.MissingDataException;
import org.neuromorpho.paperbot.search.model.KeyWord;
import org.neuromorpho.paperbot.search.model.Log;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.repository.KeyWordRepository;
import org.neuromorpho.paperbot.search.repository.PortalRepository;
import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PortalSearchFactory portalSearchFactory;

    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private KeyWordRepository keyWordRepository;

    public void launchSearch() throws Exception {
        List<Portal> portalList = portalRepository.findByActiveAndExecuted(Boolean.TRUE, Boolean.FALSE);
        for (Portal portal : portalList) {
            Boolean executed = Boolean.FALSE;
            List<KeyWord> keyWordList = keyWordRepository.findExecuted(Boolean.FALSE); 
            //For each portal update executed keywords
            Log portalLog = new Log();
            portalRepository.update(portal.getId(), "log", portalLog);

            try {
                IPortalSearch portalSearch = portalSearchFactory.launchPortalSearch(portal.getName());
                
                for (KeyWord keyWord : keyWordList) {
                    portalSearch.findArticleList(keyWord, portal);
                    keyWordRepository.update(keyWord.getId(), "executed", Boolean.TRUE);
                    portalSearch.findArticleList(keyWord, portal);
                }
                keyWordRepository.updateAll("executed", Boolean.FALSE);
                executed = Boolean.TRUE;
                portalLog.setCause("Finished");
                portalRepository.update(portal.getId(), "executed", executed);
            } catch (InterruptedException ex){
                portalLog.setCause("Interrupted by user");
            } catch (Exception ex) {
                log.error("Unknown error", ex);
                portalLog.setCause("HTTP Connection Error");
                portalRepository.update(portal.getId(), "log", portalLog);
                throw ex;
            }
            portalRepository.update(portal.getId(), "log", portalLog);
        }


    }
    

    public ArticleContentDto getArticleContent(String doi,
                                               String portalName) throws Exception {
        Portal portal = portalRepository.findByName(portalName);
        IPortalSearch portalSearch = portalSearchFactory.launchPortalSearch(portalName);
        return portalSearch.getArticleContent(doi, portal);
    }

}
