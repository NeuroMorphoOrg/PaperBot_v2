package org.neuromorpho.paperbot.article.controler.reconstructions;

import org.neuromorpho.paperbot.article.repository.ArticleSummary;
import org.neuromorpho.paperbot.article.service.reconstructions.ReconstructionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reconstructions")
public class ReconstructionsController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    
    @Autowired
    private ReconstructionsService reconstructionsService;

    

    /*
     * Returns all the labels available for the specific details
     */
    @CrossOrigin
    @RequestMapping(value = "/specificDetails", method = RequestMethod.GET)
    public List<String> findSpecificDetailsValues() {
        log.debug("Reading valid values for specificDetails");
        return reconstructionsService.findSpecificDetailsValues();

    }
    


    /*
     * Returns the count and nReconstructions for all the DB
     */
    @CrossOrigin
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public List<ArticleSummary> getSummary(
            @RequestParam Boolean expired) {
       
        log.debug("Getting reconstructions summary, expired: " + expired);
        return reconstructionsService.getSummaryReconstructions(expired);
    }

    
}
