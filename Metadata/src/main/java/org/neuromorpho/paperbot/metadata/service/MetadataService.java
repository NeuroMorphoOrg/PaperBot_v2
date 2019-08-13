/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.metadata.service;


import org.neuromorpho.paperbot.metadata.model.Metadata;
import org.neuromorpho.paperbot.metadata.repository.MetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MetadataRepository repository;

    public List<String> getDistinctByKey(String key) {
       /* key = "attributes." + key;
        log.debug("Geting metadata disctinct values for field" + key);
        List<String> valueList = mongoTemplate.getCollection(collection).distinct(key, List.class);
        return valueList;*/
       return null;
    }

    public List<Metadata> getByKey(String key) {
        log.debug("Getting metadata values for field" + key);
        return repository.findByType(key);
    }

    public List<Metadata> findAll() {
        log.debug("Getting all metadata values");
        return repository.findAll();
    }
    
}
