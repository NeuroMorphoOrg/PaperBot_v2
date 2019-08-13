package org.neuromorpho.paperbot.search.controller;

import java.util.List;

import org.neuromorpho.paperbot.search.model.Log;
import org.neuromorpho.paperbot.search.model.Portal;
import org.neuromorpho.paperbot.search.service.portal.PortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portals")
public class PortalController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PortalService portalService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home() {
        return "Portal up & running!";
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public List<Portal> findAll() {
        return portalService.findAll();
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public void update(
            @RequestBody Portal portal) {
        portalService.update(portal);
    }
    

}
