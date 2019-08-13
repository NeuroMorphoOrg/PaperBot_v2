package org.neuromorpho.paperbot.search.service;

import org.neuromorpho.paperbot.search.service.sciencedirect.PortalSearchScienceDirectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class PortalSearchFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PortalSearchPubMedService portalSearchPubMedService;

    @Autowired
    private PortalSearchNatureService portalSearchNatureService;

    @Autowired
    private PortalSearchSpringerLinkService portalSearchSpringerLinkService;

    @Autowired
    private PortalSearchScienceDirectService portalSearchScienceDirectService;

    @Autowired
    private PortalSearchWileyService portalSearchWileyService;
    
     @Autowired
    private PortalSearchGoogleScholarService portalSearchGoogleScholarService;

    public IPortalSearch launchPortalSearch(String portalName) throws Exception {
        // sets the property for selenium
        log.debug("Creating the object for portal: " + portalName);
        if (portalName.equalsIgnoreCase("PubMed") || 
                portalName.equalsIgnoreCase("PubMedCentral")) {
            return portalSearchPubMedService;
        } else if (portalName.equalsIgnoreCase("Nature")) {
            return portalSearchNatureService;
        } else if (portalName.equalsIgnoreCase("SpringerLink")) {
            return portalSearchSpringerLinkService;
        } else if (portalName.equalsIgnoreCase("ScienceDirect")) {
            return portalSearchScienceDirectService;
        } else if (portalName.equalsIgnoreCase("Wiley")) {
            return portalSearchWileyService;
        } else if (portalName.equalsIgnoreCase("GoogleScholar")) {
            return portalSearchGoogleScholarService;
        } else {
            log.warn("Unsuported Portal: " + portalName);
        }
        return null;
    }
}
