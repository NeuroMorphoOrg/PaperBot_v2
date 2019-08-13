/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service.portal;

import java.util.List;

import org.neuromorpho.paperbot.search.repository.PortalRepository;
import org.neuromorpho.paperbot.search.model.Log;
import org.neuromorpho.paperbot.search.model.Portal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PortalRepository portalRepository;

    public List<Portal> findAll() {
        log.debug("Retrieving portals from DB");
        return portalRepository.findAll();
        
    }

    public void update(Portal portal) {
        log.debug("Updating portals into DB");
        portalRepository.save(portal);
    }
    

}
